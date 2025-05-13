package com.erp.biztrack.graph.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.graph.model.dto.Graph;
import com.erp.biztrack.graph.model.service.GraphService;

@Controller
@RequestMapping("/graph")
public class GraphController {
    private static final Logger logger = LoggerFactory.getLogger(GraphController.class);

    @Autowired
    private GraphService graphService;

    // 제품별 영업이익 그래프 API
    @RequestMapping(value = "/api/profitGraph.do", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<Graph> getProfitData() {
        return (ArrayList<Graph>) graphService.selectProfitGraphData();
    }
    
    
    // 거래처별 거래건수 그래프 API
    @RequestMapping(value = "/api/transactionCountGraph.do", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<Graph> getTransactionCountData() {
        return (ArrayList<Graph>) graphService.getTransactionCountData();
    }


    // 부서별 영업실적 그래프 API
    @RequestMapping(value = "/api/departmentSalesPerformanceGraph.do", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<Graph> getDepartmentSalesData() {
        return (ArrayList<Graph>) graphService.getDepartmentSalesPerformanceData();
    }

    // 그래프 대시보드 화면
    @RequestMapping("/dashBoard.do")
    public String showDashBoardViewPage() {
        return "/graph/graphDashBoardView";
    }

    // 제품별 영업이익 그래프
    @RequestMapping("/profitGraph.do")
    public String showProfitGraph(Model model) {
        List<Graph> profitGraphList = graphService.selectProfitGraphData();
        model.addAttribute("profitGraphList", profitGraphList);
        return "/graph/profitGraphView";
    }

    // 거래건수 그래프
    @RequestMapping("/transactionCountGraph.do")
    public String showTransactionCount(Model model) {
        List<Graph> list = graphService.getTransactionCountData();
        model.addAttribute("transactionCountList", list);
        return "graph/transactionCountGraphView";
    }

    // 부서별 영업실적 그래프
    @RequestMapping("/departmentSalesPerformanceGraph.do")
    public String departmentSalesPerformanceGraph(Model model) {
        List<Graph> deptGraphList = graphService.getDepartmentSalesPerformanceData();
        model.addAttribute("deptGraphList", deptGraphList);
        return "graph/departmentSalesPerformanceGraphView";
    }
}
