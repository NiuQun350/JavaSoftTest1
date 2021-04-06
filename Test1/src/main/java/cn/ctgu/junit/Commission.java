package cn.ctgu.junit;

/**
 * 问题描述
 * 从前有一位销售人员在亚利桑那州代销密苏里军械制造厂生产的步枪配件，
 * 包括枪机（lock）、枪托（stock）和枪管（barrel）。
 * 枪机售价45美元，枪托售价30美元，枪管售价25美元。
 * 售人员每个月至少要卖出一个枪机，一个枪托和一个枪管（但是没有必要是一支完整的步枪），
 * 而制造厂的生产能力限制销售人员一个月最多只能卖出70个枪机、80个枪托和90个枪管。
 * 每走访过一个城镇之后，销售人员都要给密苏里军械厂发一封电报，汇报在这一城镇中销售枪机、枪托和枪管的数量。
 * 销售人员月末会再发一封很短的电报，通知“-1个枪机售出”。
 * 这样军械厂就知道当月的销售活动已经结束了，计算销售人员应得的佣金了。
 *
 * 佣金计算方法如下：
 * 销售总额1000美元以下（含1000美元）部分的佣金为10%，
 * 1000至1800美元之间部分的佣金为15%，超过1800美元的部分的佣金为20%。
 * 讨论：
 * 这个佣金程序可分为三个部分：输入数据处理部分，验证输入数据的有效性，销售额统计计算部分，以及佣金计算部分。
 * 此处可省略了对输入数据有效性的验证，可使用条件循环语句While来模拟对电报的处理。
 * @2021/3/17
 * @NiuQun
 */
public class Commission {

    public void telegram(String message) {}

    // 处理数据
    public void dataProcessing(String message) {}


    /**
     * 如果输入数据有效，返回true，否则返回false
     * @param lock
     * @param stock
     * @param barrel
     * @return
     */
    public boolean validData(int lock, int stock, int barrel){
         return (lock >= 0 && lock <= 70 && stock >= 0 && stock <= 80 && barrel >= 0 && barrel <= 90);
    }

    /**
     *
     * @param lock
     * @param stock
     * @param barrel
     * @return 如果输入数据合法，返回销售额的字符串(这里将int类型整数以String类型输出)，否则返回"输入数据有误"
     */
    public String salesStatistics(int lock, int stock, int barrel) {
        if (validData(lock, stock, barrel)) {
            return Integer.valueOf(lock * 45 + stock * 30 + barrel * 25).toString();
        } else {
            return "输入数据有误";
        }
    }

    /**
     * 计算佣金
     * @param lock
     * @param stock
     * @param barrel
     * @return 如果输入数据合法，返回佣金的字符串(这里将double类型小数以String类型输出)，否则"输入数据有误"
     */
    public String calculateCommission(int lock, int stock, int barrel){
        if (validData(lock, stock, barrel)) {
            int money = Integer.parseInt(salesStatistics(lock, stock, barrel));
            if (money >= 0 && money < 1000) {
                return Double.valueOf(money * 0.1).toString();
            } else if (money > 1000 && money <= 1800) {
                return Double.valueOf(100 + (money - 1000) * 0.15).toString();
            } else if (money > 1800 && money <= 7800) {
                return Double.valueOf(100 + 120 + (money - 1800) * 0.2).toString();
            } else {
                return "输入数据有误";
            }
        } else {
            return "输入数据有误";

        }
    }
}
