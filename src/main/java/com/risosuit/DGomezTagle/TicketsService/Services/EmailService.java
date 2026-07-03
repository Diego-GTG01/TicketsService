package com.risosuit.DGomezTagle.TicketsService.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mi.servidor.ip}")
    private String ip;

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
                    <a href="http://%s:4200/token?token=%s" style="background-color: #007bff; color: white; padding: 12px 24px; text-decoration: none; font-weight: bold; border-radius: 4px; display: inline-block;">
                        Verificar Cuenta
                    </a>
                </div>
                <p style="color: #999999; font-size: 12px; text-align: center; margin-top: 30px;">
                    Si no creaste esta cuenta, puedes ignorar este correo de forma segura.
                </p>
            </div>
            """.formatted(this.ip, token);

        helper.setText(htmlContenido, true);

        mailSender.send(mensaje);
    }

    public void enviarCorreoRecuperacion(String to, String token) throws MessagingException {

        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject("Recuperación de contraseña");

        String url = "http://%s:4200/recovery?token=%s".formatted(this.ip, token);

        String htmlContenido = """
        <div style="font-family: Arial, Helvetica, sans-serif; max-width: 600px; margin: 40px auto; border: 1px solid #e5e5e5; border-radius: 8px; overflow: hidden;">

            <div style="background-color: #0d6efd; padding: 20px;">
                <h2 style="color: white; margin: 0; text-align: center;">
                    Recuperación de contraseña
                </h2>
            </div>

            <div style="padding: 30px; color: #333333;">

                <p>Hola,</p>

                <p>
                    Hemos recibido una solicitud para restablecer la contraseña de tu cuenta.
                </p>

                <p>
                    Si fuiste tú quien realizó esta solicitud, haz clic en el siguiente botón para crear una nueva contraseña.
                </p>

                <div style="text-align: center; margin: 35px 0;">
                    <a href="%s"
                       style="background-color: #0d6efd;
                              color: #ffffff;
                              text-decoration: none;
                              padding: 14px 28px;
                              border-radius: 5px;
                              display: inline-block;
                              font-size: 16px;
                              font-weight: bold;">
                        Restablecer contraseña
                    </a>
                </div>

                <p>
                    Este enlace es personal y tiene una vigencia limitada por motivos de seguridad.
                </p>

                <hr style="border: none; border-top: 1px solid #eeeeee; margin: 25px 0;">

                <p style="font-size: 13px; color: #666666;">
                    Si el botón no funciona, copia y pega el siguiente enlace en tu navegador:
                </p>

                <p style="word-break: break-all; font-size: 13px;">
                    <a href="%s">%s</a>
                </p>

                <p style="margin-top: 30px; font-size: 14px; color: #666666;">
                    Si no solicitaste restablecer tu contraseña, puedes ignorar este correo de forma segura. Tu contraseña permanecerá sin cambios.
                </p>

            </div>

            <div style="background-color: #f8f9fa; padding: 15px; text-align: center; color: #888888; font-size: 12px;">
                Este es un correo generado automáticamente. Por favor, no respondas a este mensaje.
            </div>

        </div>
        """.formatted(url, url, url);

        helper.setText(htmlContenido, true);
        mailSender.send(mensaje);
    }

}
