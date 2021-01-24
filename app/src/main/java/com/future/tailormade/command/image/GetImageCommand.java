package com.future.tailormade.command.image;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.image.GetImageRequest;

public interface GetImageCommand extends Command<GetImageRequest, byte[]> {
}
