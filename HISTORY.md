# 2.0.12 / 2017-07-05

### update wd.java fix elementsByCss using="selector" issue
*   the using="selector" is wrong for webview mode. should be "css".

# 2.0.8 / 2017-06-02

### update wd.java for macaca 2.0
*   drag(double fromX, double fromY, double toX,double toY,double duration) ,remove "steps" param, system will caculate drag steps by duration, steps = duration * 40
    the same change for press(double x,double y, double duration) 
*  fix scrollTo* 

# 2.0.3 / 2017-03-02

Main commit:
[https://github.com/macacajs/wd.java/commit/12e7c5cbcbc76a65dd2a5ceb6a8fa4496cb21e0f](https://github.com/macacajs/wd.java/commit/12e7c5cbcbc76a65dd2a5ceb6a8fa4496cb21e0f
)

### fix 'getProperty' bug for iOS

detail :[https://github.com/alibaba/macaca/issues/468](https://github.com/alibaba/macaca/issues/468)

### fix 'excute' bug

for webview test,excute js script

 
# 2.0.1 / 2017-01-18

## MacacaClient.java
change "element" property to public so we can use element more conveniently

## ElementSelector.java
change return value for  "getIndex()" from "MacacaClient" to "Element" so we can use element object directly

# 2.0 / 2017-01-09
## some APIs for MacacaClient.java are deprecated

### List of deprecated APIs
// MacacaClient.java

*  clear()
*  click()
*  getProperty(String name)
*  getRect()
*  sendKeys(String keys)
*  text()

### why we delete these APIs
these APIs are actions for an Element ,not for macacaClient, to define functions for Element Object and MacacaClient,we delete these APIs in MacacaClient,and the same APIs can be found in Element.java

### how to adapt this change
if you use these deprecated APIs,you need to change this action to an Element object,for example,if you want to click an element,then you might code like this:

before :

	driver.elementById("elementId");
	driver.click();

now,you need to code like below:

	Element element = driver.elementById("elementId");
	element.click();
	

## some touch actions are added for user's convenience
### List of new APIs 

// MacacaClient.java

*  tap(double x,double y)
*  doubleTap(double x,double y)
*  press(double x,double y, double duration,int steps)
*  pinch(double x,double y,double scale, double velocity,GesturePinchType direction,double percent,int steps)
*  rotate(double rotation, double velocity)
*  drag(double fromX, double fromY, double toX,double toY,double duration, int steps)

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
