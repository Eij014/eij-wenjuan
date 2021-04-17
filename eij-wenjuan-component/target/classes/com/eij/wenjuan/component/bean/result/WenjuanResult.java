package com.eij.wenjuan.component.bean.result;

import java.util.List;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-10
 */
public class WenjuanResult {
   private List<EchartsBarOption> result;

   private List<EchartsPieOption> pieResult;

   public WenjuanResult(List<EchartsBarOption> result, List<EchartsPieOption> pieResult) {
      this.result = result;
      this.pieResult = pieResult;
   }

   public List<EchartsBarOption> getResult() {
      return result;
   }

   public void setResult(List<EchartsBarOption> result) {
      this.result = result;
   }

   public List<EchartsPieOption> getPieResult() {
      return pieResult;
   }

   public void setPieResult(List<EchartsPieOption> pieResult) {
      this.pieResult = pieResult;
   }
}
