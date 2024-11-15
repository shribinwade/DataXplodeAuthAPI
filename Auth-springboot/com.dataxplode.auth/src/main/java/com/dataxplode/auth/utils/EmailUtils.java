package com.dataxplode.auth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailUtils {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String to , String subject, String text, List<String> list){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shribinwade.100@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        if(list!=null && list.size()>0)
            message.setCc(getCcArray(list));
        javaMailSender.send(message);
    }

    private String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size()];
        for(int i =0; i< ccList.size(); i++){
            cc[i] = ccList.get(i);
        }
        return cc;
    }

    public void forgotMail(String to, String subject,String password) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("shribinwade.100@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Password Recovery</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "        .container {\n" +
                "            width: 100%;\n" +
                "            max-width: 600px;\n" +
                "            margin: 20px auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #ffffff;\n" +
                "            border: 1px solid #ddd;\n" +
                "            border-radius: 8px;\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            padding: 10px 0;\n" +
                "        }\n" +
                "        .header img {\n" +
                "            max-width: 150px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            margin: 20px 0;\n" +
                "            line-height: 1.6;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        .button {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px 20px;\n" +
                "            margin: 20px 0;\n" +
                "            background-color: #007bff;\n" +
                "            color: #ffffff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            font-size: 12px;\n" +
                "            color: #888;\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h2>Password Recovery</h2>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Dear User,</p>\n" +
                "            <p>You have requested to reset your password. </p>\n" +


                "            <p><b>Your Login details for DataXplode</b><br><b>Email: </b>"+to +" <br><b>Click here to Rest Password: </b>" + password +
                
                "            <p>Link valid only for 15 min. </p>\n" +
                "            <p>Thank you,<br>DataXplode Team</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 Your Company. All rights reserved.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        message.setContent(htmlMsg,"text/html");
        javaMailSender.send(message);

    }

  public void sendVerificationEmail(String email,String verification_url) throws MessagingException {

      // Create a simple mail message
      MimeMessage message = javaMailSender.createMimeMessage();

      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setFrom("shribinwade.100@gmail.com");
      helper.setTo(email);
      helper.setSubject("\"Account Verification\"");
      
      String htmlMsg = "<!DOCTYPE html>\n" +
              "<html>\n" +
              "<head>\n" +
              "\n" +
              "  <meta charset=\"utf-8\">\n" +
              "  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n" +
              "  <title>Email Confirmation</title>\n" +
              "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
              "  <style type=\"text/css\">\n" +
              "  /**\n" +
              "   * Google webfonts. Recommended to include the .woff version for cross-client compatibility.\n" +
              "   */\n" +
              "  @media screen {\n" +
              "    @font-face {\n" +
              "      font-family: 'Source Sans Pro';\n" +
              "      font-style: normal;\n" +
              "      font-weight: 400;\n" +
              "      src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff');\n" +
              "    }\n" +
              "    @font-face {\n" +
              "      font-family: 'Source Sans Pro';\n" +
              "      font-style: normal;\n" +
              "      font-weight: 700;\n" +
              "      src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff');\n" +
              "    }\n" +
              "  }\n" +
              "  /**\n" +
              "   * Avoid browser level font resizing.\n" +
              "   * 1. Windows Mobile\n" +
              "   * 2. iOS / OSX\n" +
              "   */\n" +
              "  body,\n" +
              "  table,\n" +
              "  td,\n" +
              "  a {\n" +
              "    -ms-text-size-adjust: 100%; /* 1 */\n" +
              "    -webkit-text-size-adjust: 100%; /* 2 */\n" +
              "  }\n" +
              "  /**\n" +
              "   * Remove extra space added to tables and cells in Outlook.\n" +
              "   */\n" +
              "  table,\n" +
              "  td {\n" +
              "    mso-table-rspace: 0pt;\n" +
              "    mso-table-lspace: 0pt;\n" +
              "  }\n" +
              "  /**\n" +
              "   * Better fluid images in Internet Explorer.\n" +
              "   */\n" +
              "  img {\n" +
              "    -ms-interpolation-mode: bicubic;\n" +
              "  }\n" +
              "  /**\n" +
              "   * Remove blue links for iOS devices.\n" +
              "   */\n" +
              "  a[x-apple-data-detectors] {\n" +
              "    font-family: inherit !important;\n" +
              "    font-size: inherit !important;\n" +
              "    font-weight: inherit !important;\n" +
              "    line-height: inherit !important;\n" +
              "    color: inherit !important;\n" +
              "    text-decoration: none !important;\n" +
              "  }\n" +
              "  /**\n" +
              "   * Fix centering issues in Android 4.4.\n" +
              "   */\n" +
              "  div[style*=\"margin: 16px 0;\"] {\n" +
              "    margin: 0 !important;\n" +
              "  }\n" +
              "  body {\n" +
              "    width: 100% !important;\n" +
              "    height: 100% !important;\n" +
              "    padding: 0 !important;\n" +
              "    margin: 0 !important;\n" +
              "  }\n" +
              "  /**\n" +
              "   * Collapse table borders to avoid space between cells.\n" +
              "   */\n" +
              "  table {\n" +
              "    border-collapse: collapse !important;\n" +
              "  }\n" +
              "  a {\n" +
              "    color: #1a82e2;\n" +
              "  }\n" +
              "  img {\n" +
              "    height: auto;\n" +
              "    line-height: 100%;\n" +
              "    text-decoration: none;\n" +
              "    border: 0;\n" +
              "    outline: none;\n" +
              "  }\n" +
              "  </style>\n" +
              "\n" +
              "</head>\n" +
              "<body style=\"background-color: #e9ecef;\">\n" +
              "\n" +
              "  <!-- start preheader -->\n" +
              "  <div class=\"preheader\" style=\"display: none; max-width: 0; max-height: 0; overflow: hidden; font-size: 1px; line-height: 1px; color: #fff; opacity: 0;\">\n" +
              "    A preheader is the short summary text that follows the subject line when an email is viewed in the inbox.\n" +
              "  </div>\n" +
              "  <!-- end preheader -->\n" +
              "\n" +
              "  <!-- start body -->\n" +
              "  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
              "\n" +
              "    <!-- start logo -->\n" +
              "    <tr>\n" +
              "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
              "        <tr>\n" +
              "        <td align=\"center\" valign=\"top\" width=\"600\">\n" +
              "        <![endif]-->\n" +
              "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
              "          <tr>\n" +
              "\n" +
              "            \n" +
              "            <td align=\"center\" valign=\"top\" style=\"padding: 36px 24px;\">\n" +
              "              <a href=\"https://www.blogdesire.com\" target=\"_blank\" style=\"display: inline-block;\">\n" +
              "                <img src=\"\" alt=\"DataXplodeLOGO\" border=\"0\" width=\"48\" style=\"display: block; width: 48px; max-width: 48px; min-width: 48px;\">\n" +
              "              </a>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "        </table>\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        </td>\n" +
              "        </tr>\n" +
              "        </table>\n" +
              "        <![endif]-->\n" +
              "      </td>\n" +
              "    </tr>\n" +
              "    <!-- end logo -->\n" +
              "\n" +
              "    <!-- start hero -->\n" +
              "    <tr>\n" +
              "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
              "        <tr>\n" +
              "        <td align=\"center\" valign=\"top\" width=\"600\">\n" +
              "        <![endif]-->\n" +
              "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
              "          <tr>\n" +
              "            <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;\">\n" +
              "              <h1 style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;\">Confirm Your Email Address</h1>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "        </table>\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        </td>\n" +
              "        </tr>\n" +
              "        </table>\n" +
              "        <![endif]-->\n" +
              "      </td>\n" +
              "    </tr>\n" +
              "    <!-- end hero -->\n" +
              "\n" +
              "    <!-- start copy block -->\n" +
              "    <tr>\n" +
              "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
              "        <tr>\n" +
              "        <td align=\"center\" valign=\"top\" width=\"600\">\n" +
              "        <![endif]-->\n" +
              "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
              "\n" +
              "          <!-- start copy -->\n" +
              "          <tr>\n" +
              "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n" +
              "              <p style=\"margin: 0;\">Tap the button below to confirm your email address. If you didn't create an account with <a href=\"\">Past</a>, you can safely delete this email.</p>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "          <!-- end copy -->\n" +
              "\n" +
              "          <!-- start button -->\n" +
              "          <tr>\n" +
              "            <td align=\"left\" bgcolor=\"#ffffff\">\n" +
              "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
              "                <tr>\n" +
              "                  <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 12px;\">\n" +
              "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
              "                      <tr>\n" +
              "                        <td align=\"center\" bgcolor=\"#1a82e2\" style=\"border-radius: 6px;\">\n" +
              "                          <a href=\""+verification_url+"\" target=\"_blank\" style=\"display: inline-block; padding: 16px 36px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; border-radius: 6px;\">Verify Email</a>\n" +
              "                        </td>\n" +
              "                      </tr>\n" +
              "                    </table>\n" +
              "                  </td>\n" +
              "                </tr>\n" +
              "              </table>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "          <!-- end button -->\n" +
              "\n" +
              "          <!-- start copy -->\n" +
              "          <tr>\n" +
              "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n" +
              "              <p style=\"margin: 0;\">If that doesn't work, copy and paste the following link in your browser:</p>\n" +
              "              <p style=\"margin: 0;\"><a href=\""+verification_url+"\" target=\"_blank\">"+verification_url+"</a></p>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "          <!-- end copy -->\n" +
              "\n" +
              "          <!-- start copy -->\n" +
              "          <tr>\n" +
              "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; border-bottom: 3px solid #d4dadf\">\n" +
              "              <p style=\"margin: 0;\">Cheers,<br> DataXplode Team</p>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "          <!-- end copy -->\n" +
              "\n" +
              "        </table>\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        </td>\n" +
              "        </tr>\n" +
              "        </table>\n" +
              "        <![endif]-->\n" +
              "      </td>\n" +
              "    </tr>\n" +
              "    <!-- end copy block -->\n" +
              "\n" +
              "    <!-- start footer -->\n" +
              "    <tr>\n" +
              "      <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 24px;\">\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
              "        <tr>\n" +
              "        <td align=\"center\" valign=\"top\" width=\"600\">\n" +
              "        <![endif]-->\n" +
              "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
              "\n" +
              "          <!-- start permission -->\n" +
              "          <tr>\n" +
              "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n" +
              "              <p style=\"margin: 0;\">You received this email because we received a request for email verification on DataXplode website. If you didn't request for email verification on DataXplode you can safely delete this email.</p>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "          <!-- end permission -->\n" +
              "\n" +
              "          <!-- start unsubscribe -->\n" +
              "          <tr>\n" +
              "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n" +
              "              <!-- <p style=\"margin: 0;\">To stop receiving these emails, you can <a href=\"https://www.blogdesire.com\" target=\"_blank\">unsubscribe</a> at any time.</p>\n" +
              "              <p style=\"margin: 0;\">Paste 1234 S. Broadway St. City, State 12345</p> -->\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "          <!-- end unsubscribe -->\n" +
              "\n" +
              "        </table>\n" +
              "        <!--[if (gte mso 9)|(IE)]>\n" +
              "        </td>\n" +
              "        </tr>\n" +
              "        </table>\n" +
              "        <![endif]-->\n" +
              "      </td>\n" +
              "    </tr>\n" +
              "    <!-- end footer -->\n" +
              "\n" +
              "  </table>\n" +
              "  <!-- end body -->\n" +
              "\n" +
              "</body>\n" +
              "</html>";


      message.setContent(htmlMsg,"text/html");
      // Send the email
      javaMailSender.send(message);

      System.out.println("Verification email sent to " + email);
  }

}

