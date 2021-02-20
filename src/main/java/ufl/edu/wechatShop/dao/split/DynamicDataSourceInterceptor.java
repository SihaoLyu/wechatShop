package ufl.edu.wechatShop.dao.split;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        Object[] objects = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement)objects[0];
        String lookupKey = null;

        if (!synchronizationActive) {
            // select key is auto increment (SELECT LAST_INSERT_ID()) then use main db
            if (mappedStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                lookupKey = DynamicDataSourceHolder.DB_MASTER;
            }
            else {
                BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
                // TODO Locale.CHINA?
                String sql = boundSql.getSql().toLowerCase(Locale.US).replaceAll("[\\t\\n\\r]", " ");
                if (sql.matches(REGEX)) {
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                }
                else {
                    lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                }
            }
        }
        else {
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }

        logger.debug("Set function[{}] use [{}] Strategy, SqlCommandType [{}]..",
                mappedStatement.getId(), lookupKey, mappedStatement.getSqlCommandType().name());
        DynamicDataSourceHolder.setDbType(lookupKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
