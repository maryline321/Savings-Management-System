package com.SavingsManagementSystem.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse {
    private boolean error;
    private String message;
}
