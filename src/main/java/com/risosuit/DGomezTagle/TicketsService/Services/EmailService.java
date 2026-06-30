/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosuit.DGomezTagle.TicketsService.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void enviarCorreoVerificacion(String to, String token) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
        
        helper.setTo(to);
        helper.setSubject("Verifica tu cuenta de correo electrónico");
        
        String htmlContenido = """
            <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 5px;">
                <h2 style="color: #333333; text-align: center;">¡Gracias por registrarte!</h2>
                <p style="color: #666666; font-size: 16px; line-height: 1.5;">
                    Por favor, confirma tu dirección de correo electrónico haciendo clic en el botón de abajo para activar tu cuenta.
                </p>
                <div style="text-align: center; margin: 30px 0;">
                    <a href="http://localhost:4200/token?token=""" + token + """
                    " style="background-color: #007bff; color: white; padding: 12px 24px; text-decoration: none; font-weight: bold; border-radius: 4px; display: inline-block;">
                        Verificar Cuenta
                    </a>
                </div>
                <p style="color: #999999; font-size: 12px; text-align: center; margin-top: 30px;">
                    Si no creaste esta cuenta, puedes ignorar este correo de forma segura.
                </p>
            </div>
            """;
        
        helper.setText(htmlContenido, true);
        
        mailSender.send(mensaje);
    }
}