/**
 * Copyright (C) 2015-2019 Philip Helger (www.helger.com)
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
package com.helger.as4.crypto;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.lang.NonBlockingProperties;
import com.helger.commons.string.ToStringGenerator;
import com.helger.security.keystore.EKeyStoreType;

/**
 * Wrapper around the crypto properties file.
 *
 * @author bayerlma
 * @author Philip Helger
 */
@Immutable
public class CryptoProperties implements Serializable
{
  public static final String KEYSTORE_TYPE = "org.apache.wss4j.crypto.merlin.keystore.type";
  public static final String KEYSTORE_FILE = "org.apache.wss4j.crypto.merlin.keystore.file";
  public static final String KEYSTORE_PASSWORD = "org.apache.wss4j.crypto.merlin.keystore.password";
  public static final String KEY_ALIAS = "org.apache.wss4j.crypto.merlin.keystore.alias";
  public static final String KEY_PASSWORD = "org.apache.wss4j.crypto.merlin.keystore.private.password";

  private NonBlockingProperties m_aProps;

  public CryptoProperties (@Nullable final Map <String, String> aProps)
  {
    m_aProps = new NonBlockingProperties ();
    if (aProps != null)
      m_aProps.putAll (aProps);
  }

  public CryptoProperties (@Nonnull final IReadableResource aRes)
  {
    ValueEnforcer.notNull (aRes, "Resource");
    if (aRes.exists ())
      try
      {
        m_aProps = new NonBlockingProperties ();
        try (final InputStream aIS = aRes.getInputStream ())
        {
          m_aProps.load (aIS);
        }
      }
      catch (final Throwable t)
      {
        throw new InitializationException ("Failed to init CryptoProperties from resource " + aRes + "!", t);
      }
  }

  public boolean isRead ()
  {
    return m_aProps != null;
  }

  @Nullable
  public Properties getAsProperties ()
  {
    if (m_aProps == null)
      return null;

    final Properties ret = new Properties ();
    ret.putAll (m_aProps);
    return ret;
  }

  @Nullable
  private String _getProperty (@Nonnull final String sName)
  {
    if (m_aProps == null)
      return null;
    return m_aProps.getProperty (sName);
  }

  @Nullable
  public EKeyStoreType getKeyStoreType ()
  {
    final String sProp = _getProperty (KEYSTORE_TYPE);
    return EKeyStoreType.getFromIDCaseInsensitiveOrDefault (sProp, EKeyStoreType.JKS);
  }

  @Nullable
  public String getKeyStorePath ()
  {
    return _getProperty (KEYSTORE_FILE);
  }

  @Nullable
  public String getKeyStorePassword ()
  {
    return _getProperty (KEYSTORE_PASSWORD);
  }

  @Nullable
  public String getKeyAlias ()
  {
    return _getProperty (KEY_ALIAS);
  }

  @Nullable
  public String getKeyPassword ()
  {
    return _getProperty (KEY_PASSWORD);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Props", m_aProps).getToString ();
  }
}
