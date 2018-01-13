package kata09.checkout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import kata09.checkout.rule.TotalPrice;

public class Rules {

    private Map<String, Object> rules = new HashMap<>();
    private Invocable processing;

    public void setProcessing(String script) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(script);
        processing = (Invocable) engine;
    }

    public void addRule(String key, Object rule) {
        rules.put(key, rule);
    }

    public int calculate(List<String> products) throws RuleException {
        TotalPrice total = new TotalPrice();
        rules.put("products", products);
        rules.put("totalPrice", total);
        try {
            processing.invokeFunction("calculate", rules);
        } catch (NoSuchMethodException | ScriptException e) {
            throw new RuleException(e);
        }
        return total.getTotal();
    }
}
