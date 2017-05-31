package com.fsmflying.sys.dm.helper;

public class LoginResult {
	private User user;
	private int result = -1;
	private String redirectUrl;
	private String message;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * ��ȡ�û���¼�����־λ�� <br/>
	 * 0:��¼ʧ��,�û������ڣ����������!  <br/>
	 * 1:��¼�ɹ�!  <br/>
	 * 2:��¼ʧ�ܣ��û���������!  <br/>
	 * 3:��¼ʧ�ܣ��˺ű�����! <br/>
	 * 4:�û������� <br/>
	 * -1:δ֪(Ĭ��ֵ)
	 * -2:�Ѿ���¼�ɹ���ת��ָ��Ĭ��ҳ(���ؽ���е�"redirectUrl"����ָ��)
	 * @return �����û���¼�����־λ
	 */
	public int getResult() {
		return result;
	}

	/**
	 * �����û���¼�����־λ��<br/>
	 *  0:��¼ʧ��,�û������ڣ����������!  <br/>
	 *  1:��¼�ɹ�!  <br/>
	 *  2:��¼ʧ�ܣ��û���������!  <br/>
	 *  3:��¼ʧ�ܣ��˺ű�����! <br/>
	 *  4:�û������� <br/>
	 * -1:δ֪(Ĭ��ֵ) <br/>
	 * @param result ��¼�����־λ
	 */
	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void success() {
		this.result = 1;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
