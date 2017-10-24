package org.immregistries.vaccination_deduplication;

import junit.framework.TestCase;

public class VerifyAPIVersion extends TestCase {
  public void test()
  {
    assertEquals("0.1", APIVersion.VERSION);
  }
}
