
package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import dao.StatDAO;
import vo.Comparison;
import vo.Item;
import vo.StatData;

public class StatAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private Comparison comparison;
	private List<Map<String, Object>> statList;
	private JSONArray statArray;

	public String comparison() {

		StatDAO dao = new StatDAO();

		comparison.setId((String) session.get("loginId"));
		comparison.setTime("0");

		statList = new ArrayList<>();
		statList = dao.comparisonData(comparison);

		statArray = new JSONArray();

		for (int i = 0; i < statList.size(); i++) {

			JSONObject statObject = new JSONObject();

		    statObject.put("average", statList.get(i).get("AVERAGE"));
			statObject.put("self", statList.get(i).get("SELF"));
			statObject.put("category", statList.get(i).get("CATEGORY"));

			System.out.println(statObject.toJSONString());

			statArray.add(statObject);

		}


		return SUCCESS;
	}

	////////////////////////////

	public Comparison getComparison() {
		return comparison;
	}

	public void setComparison(Comparison comparison) {
		this.comparison = comparison;
	}

	public JSONArray getStatArray() {
		return statArray;
	}

	public void setStatArray(JSONArray statArray) {
		this.statArray = statArray;
	}

	public List<Map<String, Object>> getStatList() {
		return statList;
	}

	public void setStatList(List<Map<String, Object>> statList) {
		this.statList = statList;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}// end of class