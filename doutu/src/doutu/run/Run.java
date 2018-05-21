package doutu.run;

import java.util.Timer;
import java.util.TimerTask;

import doutu.connection.UrlDowmLoad;
import doutu.connection.UrlParse;

public class Run {
	private static int page = 1;//页码
	
	public static void main(String[] args) {
		parseThread();//解析界面的线程方法
		downloadThread();//下载图片的线程方法
	}

	private static void downloadThread() {
		final UrlDowmLoad urlDowmLoad = new UrlDowmLoad();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				urlDowmLoad.downloadSyn();
			}
		}, 0,100);
	}

	private static void parseThread() {
		final UrlParse urlParse = new UrlParse();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("。。。。。。程序正在解析网页"+(page)+"。。。。。。");
				urlParse.parseUrl(page);
				if(page>561) {//目前最大页码是561
					System.exit(0);
				}
				page++;//页码自增，也可以注释掉，爬取指定页码
				System.out.println("。。。。。。程序解析网页完成"+(page)+"。。。。。。");
			}
		}, 0,3000);
	}
}
