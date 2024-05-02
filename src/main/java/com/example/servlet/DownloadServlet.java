package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    private int BUFFER_SIZE = 1024*1000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        File file = new File(path);
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;

        response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getName()));
        response.setContentType("application/octet-stream");

        if (file.exists()) {
            outputStream = response.getOutputStream();
            fileInputStream = new FileInputStream(file);

            byte[] bf = new byte[BUFFER_SIZE];
            int byteReader = 1;

            while ((byteReader=fileInputStream.read(bf)) != -1) {
                outputStream.write(bf, 0, byteReader);
            }
        } else {
            System.out.println("File not found="+file.getName());
        }
    }
}
