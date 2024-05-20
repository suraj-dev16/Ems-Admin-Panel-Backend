package com.example.AdminPanelBackend.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponsePayload {

	  private String fileName;
	    private String fileDownloadUri;
	    private String fileType;
	    private long size;
}
