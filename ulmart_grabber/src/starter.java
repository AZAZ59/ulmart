import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class starter {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Document doc = Jsoup
				.connect(
						"http://www.ulmart.ru/discount/notebooks?sort=0&viewType=1&rec=false")
				.timeout(0).get();
		Elements sity = doc.select(".b-cities-handle");
		System.out.println(sity.first().text());

		Elements els = doc.select(".b-product-list-item");
		for (Element e : els) {
			String status = e.select(".b-product-list-item__side")
					.select(".b-tooltip").select(".b-product-status")
					.select(".b-pseudolink").text();
			// Наличие товара ( используется в if
			int priseInt = -1;
			StringBuilder sb = new StringBuilder();
			String priseStr="";
			if (!status.equals("В резерве")) {

				Elements prise = e.select(".b-price");
				priseStr=prise.get(1).text();
				sb.append(priseStr);

				sb.delete(sb.length() - 5, sb.length()).deleteCharAt(
						sb.indexOf(" "));
				priseInt = Integer.parseInt(sb.toString());
				if (priseInt <= 8000) {
					System.out.println(status);

					System.out.println(e.select(".b-product-list-item__descr")
							.text());
					// описание

					System.out.println("Экономия: " + prise.get(0).text());
					System.out.println("Цена: " + priseStr);

					System.out.println("-----------");
				}
			}
			
		}
	}
}
