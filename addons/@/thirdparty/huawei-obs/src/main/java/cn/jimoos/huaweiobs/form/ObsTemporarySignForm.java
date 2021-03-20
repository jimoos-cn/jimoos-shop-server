package cn.jimoos.huaweiobs.form;

import lombok.Data;

import java.util.List;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/11/23 11:30
 */
@Data
public class ObsTemporarySignForm {
    private List<Blob> blobs;

    @Data
    public static class Blob {
        /**文件名*/
        private String name;
        /**
         * 0 images 1 video
         */
        private Integer type = 0;
    }
}
