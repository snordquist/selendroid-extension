#selendroid-extension

Selendroid extension to set shared preferences.

## Run the demo with Java

```java
  package io.selendroid.extension;
   
  import io.selendroid.support.BaseAndroidExtensionTest;
  import org.junit.Before;
   
  import static org.junit.Assert.assertEquals;
   
  public class ExtensionLoadTest {
    private WebDriver driver;
  
    @Before
    public void setup() {
      SelendroidCapabilities capa = new SelendroidCapabilities("io.selendroid.testapp:1.0");
      capa.setSelendroidExtensions(myExtension.dex)
      driver = new SelendroidDriver(capa);

      Map<String, String> data = new HashMap<>();
      data.put("name", "myname");
      data.put("key", "my-key");
      data.put("value", "my-value");
      driver.callExtension(SetSharedPreferencesExtensionHandler.class.getName(), data);
    }
  }
```
