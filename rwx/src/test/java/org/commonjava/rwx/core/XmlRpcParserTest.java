/*
 * Copyright (C) 2010 Red Hat, Inc. (jdcasey@commonjava.org)
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
package org.commonjava.rwx.core;

import org.commonjava.rwx.error.XmlRpcException;
import org.commonjava.rwx.model.Fault;
import org.commonjava.rwx.model.MethodCall;
import org.commonjava.rwx.model.MethodResponse;
import org.commonjava.rwx.vocab.Nil;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ruhan on 7/13/17.
 */
public class XmlRpcParserTest
                extends AbstractTest
{

    @Test
    public void simpleRequestTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "simpleRequest" ) );
        Object object = parser.parse();

        assertTrue( object instanceof MethodCall );

        MethodCall request = (MethodCall) object;

        String methodName = request.getMethodName();
        assertEquals( "foo", methodName );
    }

    @Test
    public void requestWithOneParamTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "requestWithOneParam" ) );
        Object object = parser.parse();

        MethodCall request = (MethodCall) object;
        List<Object> params = request.getParams();

        assertEquals( 1, params.size() );
        assertEquals( "test", params.get( 0 ) );

        String methodName = request.getMethodName();
        assertEquals( "foo", methodName );
    }

    @Test
    public void requestWithOneStructParamTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "requestWithOneStructParam" ) );
        Object object = parser.parse();

        MethodCall request = (MethodCall) object;
        List<Object> params = request.getParams();

        assertEquals( 1, params.size() );

        Object param = params.get( 0 );
        assertTrue( param instanceof Map );

        Map<String, Object> struct = (Map<String, Object>) param;
        assertEquals( "test", struct.get( "key" ) );

        String methodName = request.getMethodName();
        assertEquals( "foo", methodName );
    }

    @Test
    public void requestWithOneArrayParamTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "requestWithOneArrayParam" ) );
        Object object = parser.parse();

        MethodCall request = (MethodCall) object;
        List<Object> params = request.getParams();

        assertEquals( 1, params.size() );

        Object param = params.get( 0 );
        assertTrue( param instanceof List );

        List<Object> array = (List<Object>) param;
        assertEquals( "test", array.get( 0 ) );

        String methodName = request.getMethodName();
        assertEquals( "foo", methodName );
    }

    @Test
    public void requestWithArrayInStructTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "requestWithArrayInStruct" ) );
        Object object = parser.parse();

        MethodCall request = (MethodCall) object;
        List<Object> params = request.getParams();

        assertEquals( 1, params.size() );

        Object param = params.get( 0 );
        assertTrue( param instanceof Map );

        Map<String, Object> struct = (Map<String, Object>) param;

        List<Object> array = (List<Object>) struct.get( "key" );
        assertEquals( "test", array.get( 0 ) );

        String methodName = request.getMethodName();
        assertEquals( "foo", methodName );
    }

    @Test
    public void kojiMulticallRequestTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "kojiMulticallRequest" ) );
        Object object = parser.parse();

        String nvr = "org.dashbuilder-dashbuilder-parent-metadata-0.4.0.Final-1";

        MethodCall request = (MethodCall) object;
        List<Object> params = request.getParams();

        assertEquals( 1, params.size() );

        List<Object> array = (List<Object>) params.get( 0 );

        Map<String, Object> value1 = (Map<String, Object>) array.get( 0 );
        assertEquals( "getBuild", value1.get( "methodName" ) );
        assertEquals( nvr, ( (List<Object>) value1.get( "params" ) ).get( 0 ) );

        Map<String, Object> value2 = (Map<String, Object>) array.get( 1 );
        assertEquals( "listTags", value2.get( "methodName" ) );
        assertEquals( nvr, ( (List<Object>) value2.get( "params" ) ).get( 0 ) );

        String methodName = request.getMethodName();
        assertEquals( "multiCall", methodName );
    }

    @Test
    public void simpleResponseTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "simpleResponse" ) );
        Object object = parser.parse();

        assertTrue( object instanceof MethodResponse );

        MethodResponse response = (MethodResponse) object;
        List<Object> params = response.getParams();

        assertEquals( 1, params.size() );

        Object p = params.get( 0 );
        assertTrue( p instanceof Double );
        assertEquals( 18.24668429131D, p );
    }

    @Test
    public void simpleFaultResponseTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "simpleFault" ) );
        Object object = parser.parse();

        assertTrue( object instanceof Fault );

        Fault fault = (Fault) object;
        Object value = fault.getValue();

        assertTrue( value instanceof Map );

        Map<String, Object> m = (Map<String, Object>) value;

        Object p = m.get( "faultCode" );
        assertTrue( p instanceof Integer );
        assertEquals( 101, ( (Integer) p ).intValue() );

        p = m.get( "faultString" );
        assertTrue( p instanceof String );
        assertEquals("foo", p);
    }

    @Test
    public void jiraServerInfoTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "jiraServerInfoResponse" ) );
        Object object = parser.parse();

        MethodResponse response = (MethodResponse) object;

        Object p = response.getParams().get( 0 );
        assertTrue( p instanceof Map );

        Map<String, Object> struct = (Map<String, Object>) p;

        assertEquals( "4.1.2", struct.get( "version" ) );
        assertEquals( "Enterprise", struct.get( "edition" ) );
        assertEquals( 531, struct.get( "buildNumber" ) );
        assertEquals( "Mon Jun 07 00:00:00 CDT 2010", struct.get( "buildDate" ) );
    }

    @Test
    public void kojiGetBuildResponseTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "kojiGetBuildResponse" ) );
        Object object = parser.parse();

        MethodResponse response = (MethodResponse) object;

        Object p = response.getParams().get( 0 );
        assertTrue( p instanceof Map );

        Map<String, Object> struct = (Map<String, Object>) p;

        assertEquals( "org.dashbuilder-dashbuilder-parent-metadata", struct.get( "package_name" ) );
        assertSame( Nil.NIL_VALUE, struct.get( "extra" ) );
        assertEquals( "2016-09-15 21:24:04.843726", struct.get( "creation_time" ) );
        assertEquals( 48475, struct.get( "package_id" ) );
        assertEquals( struct.get( "completion_ts" ), Double.parseDouble( "1473974644.95959" ) );
    }

    @Test
    public void kojiMulticallResponseTest() throws XMLStreamException, XmlRpcException
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "kojiMulticallResponse" ) );
        Object object = parser.parse();

        MethodResponse response = (MethodResponse) object;

        Object p = response.getParams().get( 0 );
        assertTrue( p instanceof List );

        List<Object> array = (List<Object>) p;

        List<Object> array1 = (List<Object>) array.get( 0 );
        Map<String, Object> struct = (Map<String, Object>) array1.get( 0 );
        assertEquals( "org.dashbuilder-dashbuilder-parent-metadata", struct.get( "package_name" ) );
        assertEquals( Nil.NIL_VALUE, struct.get( "extra" ) );
        assertEquals( 48475, struct.get( "package_id" ) );
        assertEquals( "0.4.0.Final", struct.get( "version" ) );

        List<Object> array2 = (List<Object>) array.get( 1 );
        List<Object> array21 = (List<Object>) array2.get( 0 );

        Map<String, Object> tag1 = (Map<String, Object>) array21.get( 0 );
        assertEquals( true, tag1.get( "maven_support" ) );
        assertEquals( "jb-bxms-6.3-candidate", tag1.get( "name" ) );

        Map<String, Object> tag2 = (Map<String, Object>) array21.get( 1 );
        assertEquals( true, tag2.get( "maven_support" ) );
        assertEquals( "jb-cs-maven-candidate", tag2.get( "name" ) );

        Map<String, Object> tag3 = (Map<String, Object>) array21.get( 2 );
        assertEquals( true, tag3.get( "maven_support" ) );
        assertEquals( "jb-fis-2.0-maven-imports", tag3.get( "name" ) );

        Map<String, Object> tag4 = (Map<String, Object>) array21.get( 3 );
        assertEquals( true, tag4.get( "maven_support" ) );
        assertEquals( "jb-mm-7.0-maven-candidate", tag4.get( "name" ) );
    }

    @Test
    public void kojiGetBuildTypeNilResponseTest() throws Exception
    {
        final XmlRpcParser parser = new XmlRpcParser( getXMLStream( "kojiGetBuildTypeNilResponse" ) );
        Object object = parser.parse();

        MethodResponse response = (MethodResponse) object;

        Object p = response.getParams().get( 0 );
        assertTrue( p instanceof Map );

        Map<String, Object> struct = (Map<String, Object>) p;

        assertSame( Nil.NIL_VALUE, struct.get( "rpm" ) );
    }
}
