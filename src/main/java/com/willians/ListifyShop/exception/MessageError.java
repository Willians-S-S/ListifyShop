package com.willians.ListifyShop.exception;

import java.time.Instant;

public record MessageError (Instant timestamp, Integer status, String error, String message, String path){}
