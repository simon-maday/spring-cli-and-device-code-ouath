package com.maday.shell;

import java.util.concurrent.CompletableFuture;

record Grant(String verificationUri, CompletableFuture<String> accessToken) {
}
