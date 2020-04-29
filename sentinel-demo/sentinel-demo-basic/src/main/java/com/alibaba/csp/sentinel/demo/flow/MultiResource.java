package com.alibaba.csp.sentinel.demo.flow;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: chixiao
 * @date: 2020-03-31
 * @time: 15:15
 */
public class MultiResource {

    public static void main(String[] args) {

        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource("1");
        // set limit qps to 20
        rule1.setCount(1);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
        Entry entry = null;

        try {
            entry = SphU.entry("1");
            // token acquired, means pass
            inner();
            System.out.println("in");
        } catch (BlockException e1) {
            System.out.println("block");
        } catch (Exception e2) {
            // biz exception
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    private static void inner(){
//        List<FlowRule> rules = new ArrayList<FlowRule>();
//        FlowRule rule1 = new FlowRule();
//        rule1.setResource("1");
//        // set limit qps to 20
//        rule1.setCount(0);
//        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        rule1.setLimitApp("default");
//        rules.add(rule1);
//        FlowRuleManager.loadRules(rules);
        Entry entry = null;

        try {
            entry = SphU.entry("1");
            // token acquired, means pass
            System.out.println("in");
        } catch (BlockException e1) {
            System.out.println("block");
        } catch (Exception e2) {
            // biz exception
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }
}
