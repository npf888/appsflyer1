package com.ami.api.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于生成图形验证码
 * 
 * @author  zhuweiliang
 * @version  [版本号, Apr 23, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ImageServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	// 生成数字和字母的验证码
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		BufferedImage img = new BufferedImage(65, 22, BufferedImage.TYPE_INT_RGB);

		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, 113, 22);
		// 向图片中输出数字和字母
		StringBuffer sb = new StringBuffer();
		char[] ch = "abcdefghjkmnpqrstuvwxyz23456789".toCharArray();
		int index, len = ch.length;
		// 随机产生15条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
		for (int i = 0; i < 15; i++)
		{
			int x = r.nextInt(50);
			int y = r.nextInt(70);
			int xl = r.nextInt(12);
			int yl = r.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		for (int i = 0; i < 4; i++)
		{
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的字体和大小
			g.drawString("" + ch[index], (i * 15) + 3, 18);// 写什么数字，在图片的什么位置画
			sb.append(ch[index]);
		}
		request.getSession().setAttribute("piccode", sb.toString());
		ImageIO.write(img, "JPG", response.getOutputStream());
	}
}
