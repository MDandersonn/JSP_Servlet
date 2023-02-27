package listener;

import jakarta.servlet.http.HttpSessionActivationListener;

public class MyHttpSessionActivationListener implements HttpSessionActivationListener {
//세션 유효시간이 있다. 기본30분인데 유효시간안에 활성화 되었는지 비활성화되었는지 추적해서 작업 (활성/비활성감지해서 작업하기)
}
