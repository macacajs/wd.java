# 1.0.48 /2016-12-28

*  Change return type for all elementBy..  APIs

	before this change,all elementBy.. APIs returns the current instance of MacacaClient,that means we can not operate an element directly ,it limits our usage about elements, in this change,we change the return type for all elementBy.. method to an Element object,so we can operate our target element more flexible.

	take one of these APIs as example:

	before:
	
		// MacacaClient.java
		/**
		 * <p>
	 	* Search for an element on the page, starting from the document root.<br>
	 	* Support: Android iOS Web(WebView)
	 	*
		 * @param elementId
		 *            The ID attribute of element
	 	* @return return the current instance of MacacaClient
	 	* @throws Exception
	 	*/
		public Element elementById(String elementId) throws Exception {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", elementId);
			jsonObject.put("using", "id");
			element.findElement(jsonObject);
			return this;
		}

	now:
	
		// MacacaClient.java
		/**
	 	* <p>
	 	* Search for an element on the page, starting from the document root.<br>
	 	* Support: Android iOS Web(WebView)
	 	*
		 * @param elementId
	 	*            The ID attribute of element
	 	* @return return the element to find if it exists,if it does not exist ,return null
		 * @throws Exception
		 */
		public Element elementById(String elementId) throws Exception {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", elementId);
			jsonObject.put("using", "id");
			boolean isExist = element.findElement(jsonObject);
			return isExist ? element : null;
		}
	
	

	with this change,you can operate your element as you need, like this:
	
		Element element = driver.elementById("your_element_id");
		boolean isElementDisplayed = element.isDisplayed();
	
	but if you use chain syntax before,this change will break your chain, because it does not 	return MacacaClient anymore, if you use like this before:
	
		driver.elementById("your_element_id").click();

	you need to break this chain like this:

		driver.elementById("your_element_id");
		driver.click();
	

# 1.0.26 / 2016-09-30

  * Add waitForElementByLinkText() and waitForElementByPartialLinkText() function.

# 1.0.18 / 2016-09-26

  * Fix swipe

# 1.0.16 / 2016-09-23

  * Fix isElementExist() function bug:replace setValue() to findElement()

# 1.0.15 / 2016-09-23

  * Fix isDisplayed() function bug:java.lang.Boolean cannot be cast to com.alibaba.fastjson.JSONObject

# 1.0.14 / 2016-09-23

  * Fix isDisplayed() function bug:java.lang.Boolean cannot be cast to java.lang.String

# 1.0.13 / 2016-09-23

  * Fix isElementExist() function bug by replace hasElement() to isDisplayed()

# 1.0.10 / 2016-09-20

  * Support custom host and port
  * Add api getProperty

# 1.0.5 / 2016-09-07

  * Add text API

# 1.0.4 / 2016-09-07

  * Fixed maximize API

# 1.0.3 / 2016-09-07

  * Add a series of API
  * back
  * clear
  * maximize
  * elementByClassName
  * elementByLinkText
  * elementByTagName
  * elementByPartialLinkText
  * elementsByName
  * elementsById
  * elementsByClassName
  * elementsByCss
  * elementsByLinkText
  * elementsByPartialLinkText
  * elementsByTagName

# 1.0.2 / 2016-08-26

  * Add waitForElement API

# 1.0.1 / 2016-08-25

  * Add saveScreenshot API

# 1.0.0 / 2016-08-09

  * First Commit
