/**
 * Copyright (C) 2015-2016 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.as4lib.model.pmode;

import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.as4lib.AS4TestRule;

/**
 * Test class for class {@link PMode}.
 *
 * @author Philip Helger
 */
public final class PModeTest
{
  @Rule
  public final TestRule m_aTestRule = new AS4TestRule ();

  @Test
  public void testInvalidCtor ()
  {
    try
    {
      new PMode ((String) null);
      fail ();
    }
    catch (final NullPointerException ex)
    {
      // Expected
    }
    try
    {
      new PMode ("");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {
      // Expected
    }
  }
}
