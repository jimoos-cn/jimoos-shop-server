package cn.jimoos.conf;

import java.util.Comparator;

/**
 * 支持 包含int 资源的优先
 *
 * @author :keepcleargas
 * @date :2021-01-25 10:20.
 */
public class ChangeLogComparator implements Comparator<String> {
    public static final String INIT = "init";

    public ChangeLogComparator() {
    }

    @Override
    public int compare(String o1, String o2) {
        if (o1.contains(INIT) && !o2.contains(INIT)) {
            return -1;
        } else if (o2.contains(INIT) && !o1.contains(INIT)) {
            return 1;
        } else {
            return o1.replace("WEB-INF/classes/", "").compareTo(o2.replace("WEB-INF/classes/", ""));
        }
    }
}
