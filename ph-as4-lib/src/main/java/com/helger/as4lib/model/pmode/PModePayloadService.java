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

import javax.annotation.Nullable;

import com.helger.as4lib.attachment.EAS4CompressionMode;
import com.helger.commons.hashcode.HashCodeGenerator;

public class PModePayloadService
{

  private EAS4CompressionMode m_aCompressionMode;

  public PModePayloadService (@Nullable final EAS4CompressionMode aCompressionMode)
  {
    m_aCompressionMode = aCompressionMode;
  }

  public EAS4CompressionMode getCompressionMode ()
  {
    return m_aCompressionMode;
  }

  public void setCompressionMode (final EAS4CompressionMode aCompressionMode)
  {
    m_aCompressionMode = aCompressionMode;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PModePayloadService rhs = (PModePayloadService) o;
    return m_aCompressionMode.equals (rhs.m_aCompressionMode);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aCompressionMode).getHashCode ();
  }
}
