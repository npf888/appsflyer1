package com.ami.api.db;

import java.io.Serializable;

/**
 * 数据库操作类
 * </p>
 * <p>
 * Description:输出参数名，数据类型，值封装类
 *
 */
public class OutputPara implements Serializable
{
    /**
     * 自动生成
     */
    private static final long serialVersionUID = 1L;

    /**
     * 输出参数类型
     */
    private int sqlTypes;

    /**
     * 输出参数值
     */
    private Object value;

    /**
     * 输出参数名
     */
    private String name;

    /**
     * 默认构造方法
     *
     */
    public OutputPara()
    {
    }

    /**
     * 重写equals方法
     *
     * @param name 执行存储过程可以根据这个名字取得返回值
     * @param sqlTypes 输入参数的数据类型
     */
    public OutputPara(String name, int sqlTypes)
    {
        this.name = name;
        this.sqlTypes = sqlTypes;
    }

    public void setSqlTypes(int sqlTypes)
    {
        this.sqlTypes = sqlTypes;
    }

    public int getSqlTypes()
    {
        return this.sqlTypes;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * 重写equals方法
     *
     * @param o 要比较的对象
     * @return true表示二个对象内容相等,false不等
     */
    @Override
	public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof OutputPara))
        {
            return false;
        }

        final OutputPara outputPara = (OutputPara)o;

        if (sqlTypes != outputPara.sqlTypes)
        {
            return false;
        }
        if (name != null ? !name.equals(outputPara.name) : outputPara.name != null)
        {
            return false;
        }
        if (value != null ? !value.equals(outputPara.value) : outputPara.value != null)
        {
            return false;
        }

        return true;
    }

    /**
     * 重写hashCode方法
     *
     * @return hashCode
     */
    @Override
	public int hashCode()
    {
        int result;
        result = sqlTypes;
        result = result + (value != null ? value.hashCode() : 0);
        result = result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * 重写toString方法
     *
     * @return 返回参数信息
     */
    @Override
	public String toString()
    {
        return "OutputPara{" + "sqlTypes=" + sqlTypes + ", value=" + value + ", name='" + name + "'" + "}";
    }
}
