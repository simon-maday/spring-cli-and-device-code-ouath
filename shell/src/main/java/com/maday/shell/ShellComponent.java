package com.maday.shell;

import org.springframework.shell.core.command.annotation.Command;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Component
class ShellComponent {

    private final RestClient http;
    private final Granter granter;
    private final AtomicReference<String> code = new AtomicReference<>();

    ShellComponent(RestClient.Builder builder, Granter granter) {
        this.http = builder.build();
        this.granter = granter;
    }

    @Command(description = "returns the message from a secured http endpoint")
    String message() throws ExecutionException, InterruptedException {
        if (this.code.get() == null) {
            var result = this.granter.grant();
            IO.println("Please go to " + result.verificationUri());
            var code = result.accessToken().get();
            this.code.set(code);
        }
        return this.http
                .get()
                .uri("http://localhost:8085/rest")
                .headers(h -> h.setBearerAuth(this.code.get()))
                .retrieve()
                .body(JsonNode.class)
                .get("name")
                .asString();
    }
}
