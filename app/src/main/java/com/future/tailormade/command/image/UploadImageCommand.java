package com.future.tailormade.command.image;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.image.UploadImageRequest;
import com.future.tailormade.payload.response.image.UploadImageResponse;

public interface UploadImageCommand extends Command<UploadImageRequest, UploadImageResponse> {
}
