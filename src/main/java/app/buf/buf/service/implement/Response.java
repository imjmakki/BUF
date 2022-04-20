package app.buf.buf.service.implement;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
