/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudinary;

/**
 *
 * @author shannah
 */
public class UnknownError extends RuntimeException {
    public UnknownError(String msg) {
        super(msg);
    }
}
