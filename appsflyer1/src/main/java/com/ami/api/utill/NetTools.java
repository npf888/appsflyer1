package com.ami.api.utill;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: Q3 ISP 数据处理程序
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author t.yang
 * @version 2.0.1
 */
public class NetTools
{
    private List filelist = new ArrayList();
    
    private String hostname;
    
    private String username;
    
    private String password;
    
    private int port;
    
    private Connection conn;
    
    private HashMap ipmaskMap = new HashMap();
    
    private String keyfile = "";
    //设置检查端口是否可用 连接超时时间为2000毫秒
    private int ISCONN_TIMEOUT=1500;
    
    // private Session sess;
    private SCPClient client;
    
    /**
     * 构造函数,port为指定
     * 
     * @param hostname String
     * @param username String
     * @param password String
     * @param port int
     */
    public NetTools(String hostname, int port)
    {
        this.hostname = hostname;
        this.port = port;
        this.conn = new Connection(this.hostname, this.port);
    }
    
    public NetTools(String hostname, String username, String password, String keyfile)
    {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = 22;
        this.keyfile = keyfile;
        this.conn = new Connection(this.hostname);
        
    }
    
    /**
     * 构造函数,port为指定
     * 
     * @param hostname String
     * @param username String
     * @param password String
     * @param port int
     */
    public NetTools(String hostname, String username, String password, int port, String keyfile)
    {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
        this.keyfile = keyfile;
        this.conn = new Connection(this.hostname, this.port);
    }
    
    /**
     * 检查ip+端口是否可用 <功能详细描述>
     * 
     * @return 端口可用返回 true 否则false
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public boolean checkPortAvailable()
    {
        
        Socket socket = null;
        try
        {
            socket = new Socket();
            socket.connect(new InetSocketAddress(this.hostname, this.port),ISCONN_TIMEOUT);
            
        }
        catch (Exception e)
        {
            return false;
        }
        
        finally
        {
            if (null != socket)
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }
        
        return true;
    }
    
    /**
     * 连接,中间用filePushRemote的端口做了一下测试
     * 
     * @return boolean - true连接成功 - false连接失败
     * @throws IOException
     */
    public boolean login()
    {
        boolean isAuthenticated = true;
        // 首先判断远程主机能否登陆
        try
        {
            //连接之前先检查一下端口是否可用
            if (!this.checkPortAvailable())
            {
                return false;
            }
            conn.connect();
            if (this.keyfile.equals(""))
            {
                // 普通方式认证
                isAuthenticated = conn.authenticateWithPassword(this.username, this.password);
            }
            else
            {
                // key方式认证
                File filekey = new File(this.keyfile);
                isAuthenticated = conn.authenticateWithPublicKey(this.username, filekey, this.password);
            }
            client = new SCPClient(this.conn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            isAuthenticated = false;
        }
        return isAuthenticated;
    }
    
    /**
     * 发送远程命令
     * 
     * @param command String
     * @return String
     * @throws IOException
     */
    public String sendRemoteCommand(String command)
        throws IOException
    {
        String retLine = "";
        Session sess = conn.openSession();
        try
        {
            sess.execCommand(command);
            
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                {
                    break;
                }
                else
                {
                    retLine = retLine + " " + line;
                }
            }
            stdout.close();
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            sess.close();
            System.out.println("close!");
        }
        return retLine;
    }
    
    /**
     * 加入要传输的文件
     * 
     * @param fileAllPathName String
     */
    public void SetFile(String fileAllPathName)
    {
        filelist.add(fileAllPathName);
    }
    
    /**
     * 传输文件
     * 
     * @param remotepath String - 要传输的远程主机的路径
     * @return boolean - true传输成功 false没有定义要传输的文件
     * @throws IOException
     */
    public boolean pushfile()
        throws IOException
    {
        if ((this.filelist == null) || (this.filelist.size() == 0))
        {
            return false;
        }
        else
        {
            try
            {
                String[] newArr = new String[this.filelist.size()];
                // 传送文件
                for (int i = 0; i < newArr.length; i++)
                {
                    newArr[i] = (String)this.filelist.get(i);
                }
                client.put(newArr, "/root/");// this.pcubepath
                
                // netCommforDUI s = new netCommforDUI(this.hostname,
                // this.username, this.password, this.port,this.keyfile);
                // String filename;
                // if (s.login()) {
                // for (int i = 0; i < newArr.length; i++) {
                // //从newArr中返回文件名称
                // filename = this.retFileName(newArr[i]);
                // System.out.println("filename:"+filename);
                // s.sendRemoteCommand("chmod a+rw /root/" + filename);
                // //" + this.pcubepath + "
                // }
                // }
                // s.disconnect();
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return true;
        }
    }
    
    private void chmodFile()
        throws IOException
    {
        String filename;
        String[] newArr = new String[this.filelist.size()];
        // 传送文件
        for (int i = 0; i < newArr.length; i++)
        {
            newArr[i] = (String)this.filelist.get(i);
        }
        
        for (int i = 0; i < newArr.length; i++)
        {
            // 从newArr中返回文件名称
            filename = this.retFileName(newArr[i]);
            this.sendRemoteCommand("chmod a+rw /root/" + filename);
        }
    }
    
    private String retFileName(String oldstring)
    {
        String[] s = oldstring.split("/");
        return s[s.length - 1];
    }
    
    // ============================================================================================================================================
    /**
     * 注册一个对应的监听
     * 
     * @param dsntype String
     * @param calltype String
     * @param intercept_id long
     * @param amilic_ipaddr String
     * @param amilic_port int
     * @return boolean
     * @throws InterruptedException
     * @throws IOException
     */
    public boolean regIntercept_sub(String imsiid, long intercept_id, String amilic_ipaddr, int amilic_port)
        throws InterruptedException, IOException
    {
        int i = 0;
        boolean returnOk = false;
        byte[] buffer = new byte[1024];
        try
        {
            
            StringBuffer sb = new StringBuffer();
            // sb.append("cd " + this.pcubepath + "\r");
            // sb.append("./p3subsdb --import --file=");
            // sb.append(filename);
            // sb.append("\rexit\rlogout\r");
            
            // 这里列出的只是指令本身
            // 前后可能还有一些指令需要尝试
            sb.append("lawful-intercept imsi " + imsiid + " calltype pdsn li-context li intercept-id " + String.valueOf(intercept_id));
            sb.append(" content-delivery udp-unack-format-1 dest-addr " + amilic_ipaddr + " dest-port " + amilic_port);
            sb.append(" event-delivery udp-unack-format-1 dest-addr " + amilic_ipaddr + " dest-port " + amilic_port + "\n\r");
            System.out.println("=============================================================================");
            System.out.println("----importSubscriberToDUI-----sb.toString():" + sb.toString());
            System.out.println("=============================================================================");
            Session sess = conn.openSession();
            sess.requestDumbPTY();
            sess.startShell();
            
            InputStream stdout = sess.getStdout();
            InputStream stderr = sess.getStderr();
            
            BufferedOutputStream stdin = new BufferedOutputStream(sess.getStdin());
            
            stdin.write((sb.toString()).getBytes());
            stdin.flush();
            stdin.close();
            
            while (true)
            {
                if ((stdout.available() == 0) && (stderr.available() == 0))
                {
                    int conditions = sess.waitForCondition(ChannelCondition.STDOUT_DATA | ChannelCondition.STDERR_DATA | ChannelCondition.EOF, 2000);
                    if ((conditions & ChannelCondition.TIMEOUT) != 0)
                    {
                        throw new IOException("Timeout while waiting for data from peer.");
                    }
                    if ((conditions & ChannelCondition.EOF) != 0)
                    {
                        if ((conditions & (ChannelCondition.STDOUT_DATA | ChannelCondition.STDERR_DATA)) == 0)
                        {
                            break;
                        }
                    }
                }
                
                if (stdout.available() > 0)
                {
                    
                    int len = stdout.read(buffer);
                    if (len > 0)
                    {
                        stdout = new StreamGobbler(sess.getStdout());
                        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                        String line = "";
                        while ((line = br.readLine()) != null)
                        {
                            System.out.println(line);
                            if (line.indexOf("Successful") > -1)
                            {
                                System.out.println("[Succ Reg imsid]" + imsiid);
                                returnOk = true;
                                return true;
                            }
                        }
                    }
                }
            }
            returnOk = true;
            sess.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return returnOk;
        }
    }
    
    // ============================================================================================================================================
    /**
     * 释放一个对应的监听
     * 
     * @param dsntype String
     * @param calltype String
     * @param intercept_id long
     * @param amilic_ipaddr String
     * @param amilic_port int
     * @return boolean
     * @throws InterruptedException
     * @throws IOException
     */
    // public boolean releaseIntercept_sub(String subtype,String calltype,long
    // intercept_id) throws InterruptedException, IOException {
    public boolean releaseIntercept_sub(String imsi, long intercept_id)
        throws InterruptedException, IOException
    {
        int i = 0;
        boolean returnOk = false;
        byte[] buffer = new byte[1024];
        try
        {
            
            StringBuffer sb = new StringBuffer();
            // sb.append("cd " + this.pcubepath + "\r");
            // sb.append("./p3subsdb --import --file=");
            // sb.append(filename);
            // sb.append("\rexit\rlogout\r");
            
            // 这里列出的只是指令本身
            // 前后可能还有一些指令需要尝试
            // sb.append("no lawful-intercept "+subtype+" calltype "+calltype+"
            // intercept-id "+String.valueOf(intercept_id)+"\n\r");
            sb.append("no lawful-intercept imsi " + imsi + " intercept-id " + String.valueOf(intercept_id) + "\n\r");
            System.out.println("=============================================================================");
            System.out.println("----importSubscriberToDUI-----sb.toString():" + sb.toString());
            System.out.println("=============================================================================");
            
            Session sess = conn.openSession();
            sess.requestDumbPTY();
            sess.startShell();
            
            InputStream stdout = sess.getStdout();
            InputStream stderr = sess.getStderr();
            
            BufferedOutputStream stdin = new BufferedOutputStream(sess.getStdin());
            
            stdin.write((sb.toString()).getBytes());
            stdin.flush();
            stdin.close();
            
            while (true)
            {
                if ((stdout.available() == 0) && (stderr.available() == 0))
                {
                    int conditions = sess.waitForCondition(ChannelCondition.STDOUT_DATA | ChannelCondition.STDERR_DATA | ChannelCondition.EOF, 2000);
                    if ((conditions & ChannelCondition.TIMEOUT) != 0)
                    {
                        throw new IOException("Timeout while waiting for data from peer.");
                    }
                    if ((conditions & ChannelCondition.EOF) != 0)
                    {
                        if ((conditions & (ChannelCondition.STDOUT_DATA | ChannelCondition.STDERR_DATA)) == 0)
                        {
                            break;
                        }
                    }
                }
                
                if (stdout.available() > 0)
                {
                    
                    int len = stdout.read(buffer);
                    if (len > 0)
                    {
                        stdout = new StreamGobbler(sess.getStdout());
                        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                        String line = "";
                        while ((line = br.readLine()) != null)
                        {
                            System.out.println(line);
                            
                            if (line.indexOf("Successful") > -1)
                            {
                                returnOk = true;
                                return true;
                            }
                        }
                    }
                }
            }
            returnOk = true;
            sess.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return returnOk;
        }
    }
    
    /**
     * 关闭连接
     */
    public void disconnect()
    {
        // this.sess.close();
        this.conn.close();
    }
    
    public static void main(String[] args)
        throws IOException, InterruptedException
    {
        // NetTools net = new NetTools("192.168.0.144", "staradmin", "starent",
        // 22, "");
        // boolean login = net.login();
        // if (login)
        // {
        // // System.out.println("begin");
        // // boolean ok=net.regIntercept_sub("imsi 460036040700004", "pdsn
        // // li-context li", 10002, "192.168.0.77", 9570);
        // // System.out.println("end");
        // // System.out.println("OK==================================="+ok);
        // // // System.out.println("login ok!");
        // // net.releaseIntercept_sub("imsi 460036040700004", "pdsn li-context
        // // li", 288798);
        // net.releaseIntercept_sub("", 10003);
        // System.out.println("end");
        // }
        // else
        // {
        // System.out.println("login failure!");
        //        }
        //        System.out.println("go------------------------------");
        //        net.disconnect();
        //        
        
        NetTools s = new NetTools("192.168.0.126",9567);
        
        System.out.println(s.checkPortAvailable());
        
    }
    
    public HashMap getIpmaskMap()
    {
        return ipmaskMap;
    }
}
