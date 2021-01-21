package com.future.tailormade.command.image;

import com.blibli.oss.command.Command;
import com.future.tailormade.payload.request.image.AddImageRequest;

public interface AddImageCommand extends Command<AddImageRequest, String> {
}
