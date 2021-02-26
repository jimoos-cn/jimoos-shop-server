package cn.jimoos.conf;

import liquibase.changelog.IncludeAllFilter;

/**
 * 过滤 sql 变更日志
 *
 * @author :keepcleargas
 * @date :2019-04-28 17:38.
 */
public class ChangeLogFilter implements IncludeAllFilter {
    @Override
    public boolean include(String changeLogPath) {
        return changeLogPath.endsWith(".sql");
    }
}
