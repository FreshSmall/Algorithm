package com.parser;


import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectGroupByClause;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

/**
 * @author: yinchao
 * @ClassName: SqlParserDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/7/5 17:55
 */
public class SqlParserDemo {

    public static void main(String[] args){
        String sql = "SELECT a.col1, a.col2,b.col4 FROM myTable a,myTable1 b WHERE a.col3 = b.col3";
        // 解析
        List<SQLStatement> statements = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        // 只考虑一条语句
        SQLStatement statement = statements.get(0);
        // 只考虑查询语句
        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) statement;
        SQLSelectQuery sqlSelectQuery     = sqlSelectStatement.getSelect().getQuery();
        // 非union的查询语句
        if (sqlSelectQuery instanceof SQLSelectQueryBlock) {
            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;
            // 获取字段列表
            List<SQLSelectItem> selectItems         = sqlSelectQueryBlock.getSelectList();
            selectItems.forEach(x -> {
                // 处理---------------------
                System.out.println("查询字段名称："+x.toString());
            });
            // 获取表
            SQLTableSource table = sqlSelectQueryBlock.getFrom();
            System.out.println("查询表名："+table.toString());
            // 普通单表
            if (table instanceof SQLExprTableSource) {
                // 处理---------------------
                // join多表
            } else if (table instanceof SQLJoinTableSource) {
                // 处理---------------------
                // 子查询作为表
            } else if (table instanceof SQLSubqueryTableSource) {
                // 处理---------------------
            }
            // 获取where条件
            SQLExpr where = sqlSelectQueryBlock.getWhere();
            System.out.println("查询where条件："+where.toString());
            // 如果是二元表达式
            if (where instanceof SQLBinaryOpExpr) {
                SQLBinaryOpExpr   sqlBinaryOpExpr = (SQLBinaryOpExpr) where;
                SQLExpr           left            = sqlBinaryOpExpr.getLeft();
                SQLBinaryOperator operator        = sqlBinaryOpExpr.getOperator();
                SQLExpr           right           = sqlBinaryOpExpr.getRight();
                // 处理---------------------
                // 如果是子查询
            } else if (where instanceof SQLInSubQueryExpr) {
                SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) where;
                // 处理---------------------
            }
            // 获取分组
            SQLSelectGroupByClause groupBy = sqlSelectQueryBlock.getGroupBy();
            // 处理---------------------
            // 获取排序
            SQLOrderBy orderBy = sqlSelectQueryBlock.getOrderBy();
            // 处理---------------------
            // 获取分页
            SQLLimit limit = sqlSelectQueryBlock.getLimit();
            // 处理---------------------
            // union的查询语句
        } else if (sqlSelectQuery instanceof SQLUnionQuery) {
            // 处理---------------------
        }
    }
}
