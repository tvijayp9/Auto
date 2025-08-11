
package com.nexus.util.DR.traderoute;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

//import sax.helpers.AttributesImpl;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
//import tfclient.transactions.*;

/**
 * A sample SAX2 writer. This sample program illustrates how to
 * register a SAX2 ContentHandler and receive the callbacks in
 * order to print a document that is parsed.
 *
 * @author Andy Clark, IBM
 *
 * @version $Id: SAXTransactionReaderExport.java,v 1.1.1.1 2003/09/03 05:25:33 oscarp Exp $
 */
public class SAXTransactionReaderExport extends DefaultHandler implements LexicalHandler
{
    Logger log=Logger.getLogger(SAXTransactionReaderExport.class);
    /** Namespaces feature id (http://xml.org/sax/features/namespaces). */
    protected static final String NAMESPACES_FEATURE_ID = "http://xml.org/sax/features/namespaces";
    
    /** Validation feature id (http://xml.org/sax/features/validation). */
    protected static final String VALIDATION_FEATURE_ID = "http://xml.org/sax/features/validation";
    
    /** Schema validation feature id (http://apache.org/xml/features/validation/schema). */
    protected static final String SCHEMA_VALIDATION_FEATURE_ID = "http://apache.org/xml/features/validation/schema";
    
    /** Schema full checking feature id (http://apache.org/xml/features/validation/schema-full-checking). */
    protected static final String SCHEMA_FULL_CHECKING_FEATURE_ID = "http://apache.org/xml/features/validation/schema-full-checking";
    
    /** Lexical handler property id (http://xml.org/sax/properties/lexical-handler). */
    protected static final String LEXICAL_HANDLER_PROPERTY_ID = "http://xml.org/sax/properties/lexical-handler";
    
    /** Default parser name. */
    protected static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
    
    /** Default namespaces support (true). */
    protected static final boolean DEFAULT_NAMESPACES = true;
    
    /** Default validation support (false). */
    protected static final boolean DEFAULT_VALIDATION = false;
    
    /** Default Schema validation support (false). */
    protected static final boolean DEFAULT_SCHEMA_VALIDATION = false;
    
    /** Default Schema full checking support (false). */
    protected static final boolean DEFAULT_SCHEMA_FULL_CHECKING = false;
    
    /** Default canonical output (false). */
    protected static final boolean DEFAULT_CANONICAL = false;    

    
    private String currElementName = "";
    
    private TransactionObject transObject = null;
    
    XMLReader parser = null;
        
    /** used to indicate when a value has been checked allready */
    boolean TRANSACTIONTYPE_Done = false;
    boolean DOCCREATORTPID_Done = false;
    boolean CREATIONDATE_Done = false;
    boolean USERLIST_Done = false;
    boolean TRANSACTIONCENTREDESC_Done = false;
    boolean TRADEFORMSTATUS_Done = false;
    boolean TRADEROUTEAPP_Done = false;
    boolean FILENAME_Done = false;
    boolean OrderNumber_Done = false;
    boolean GeneralNote_Found = false;
    boolean GeneralNote_Done = false;
    
    /** Print writer. */
    protected PrintWriter fOut;
    
    /** Canonical output. */
    protected boolean fCanonical;
    
    /** Element depth. */
    protected int fElementDepth;
    
    
    /** Default constructor. */
    public SAXTransactionReaderExport()
    {
    }
    
    /** Sets whether output is canonical. */
    public void setCanonical(boolean canonical)
    {
        fCanonical = canonical;
    }
    
    /** Sets the output stream for printing. */
    public void setOutput(OutputStream stream, String encoding)
    throws UnsupportedEncodingException
    {        
        if (encoding == null)
        {
            encoding = "UTF8";
        }        
        java.io.Writer writer = new OutputStreamWriter(stream, encoding);
        fOut = new PrintWriter(writer);        
    }
    
    /** Sets the output writer. */
    public void setOutput(java.io.Writer writer)
    {        
        fOut = writer instanceof PrintWriter
        ? (PrintWriter)writer : new PrintWriter(writer);        
    }
    
    /** Start document. */
    public void startDocument() throws SAXException
    {          
    }
    
    /** Processing instruction. */
    public void processingInstruction(String target, String data)
    throws SAXException
    {        
        if (fElementDepth > 0)
        {
            fOut.print("<?");
            fOut.print(target);
            if (data != null && data.length() > 0)
            {
                fOut.print(' ');
                fOut.print(data);
            }
            fOut.print("?>");
            fOut.flush();
        }        
    }
    
    /** Start element. */
    public void startElement(String uri, String local, String raw, Attributes attrs) throws SAXException
    {
        if(TRANSACTIONTYPE_Done && DOCCREATORTPID_Done && CREATIONDATE_Done && USERLIST_Done &&
        TRANSACTIONCENTREDESC_Done && TRADEFORMSTATUS_Done && TRADEROUTEAPP_Done && FILENAME_Done 
        && OrderNumber_Done && GeneralNote_Done)
        {
            /** throw finished exception */
            throw new SAXException("Finished");            
        }        
                
        currElementName = local.toString();
    }
        
    /** Characters. */
    public void characters(char ch[], int start, int length)
    throws SAXException
    {        
        if(transObject == null)
        {
            log.info("\n*** transObject == null*** \n");
            transObject = new TransactionObject();
        }
                
        if (currElementName.equalsIgnoreCase("TRANSACTIONTYPE"))
        {
            if (!TRANSACTIONTYPE_Done)
            {
                String val = getStringValue(ch, start);                
                if (transObject != null)
                {
                    transObject.setTransType(val);
                }                
                currElementName = "";
                TRANSACTIONTYPE_Done = true;
            }
        }
        
        if (currElementName.equalsIgnoreCase("CREATIONDATE"))
        {
            if (!CREATIONDATE_Done)
            {
                String val = getStringValue(ch, start);                
                if (transObject != null)
                {
                    transObject.setCreationDate(val);
                }                
                currElementName = "";
                CREATIONDATE_Done = true;
            }
        }
        
        if (currElementName.equalsIgnoreCase("DOCCREATORTPID"))
        {
            if (!DOCCREATORTPID_Done)
            {
                String val = getStringValue(ch, start);                
                if (transObject != null)
                {
                    transObject.setTransCreator(val);
                }                
                currElementName = "";
                DOCCREATORTPID_Done = true;
            }
        }
        
        if (currElementName.equalsIgnoreCase("USERLIST"))
        {
            if (!USERLIST_Done)
            {
                String val = getStringValue(ch, start);
                
                if (transObject != null)
                {
                    transObject.setUserName(val);
                }
                
                currElementName = "";
                USERLIST_Done = true;
            }
        }
        
        if (currElementName.equalsIgnoreCase("TRANSACTIONCENTREDESC"))
        {
            if (!TRANSACTIONCENTREDESC_Done)
            {
                String val = getStringValue(ch, start);
                
                if (transObject != null)
                {
                    transObject.setFileDescription(val);
                }
                
                currElementName = "";
                TRANSACTIONCENTREDESC_Done = true;
            }
        }
        
        if (currElementName.equalsIgnoreCase("TRADEFORMSTATUS"))
        {
            if (!TRADEFORMSTATUS_Done)
            {
                String val = getStringValue(ch, start);
                
                if (transObject != null)
                {
                    transObject.setFileStatus(val);
                }
                
                currElementName = "";
                TRADEFORMSTATUS_Done = true;
            }
        }
        
        if (currElementName.equalsIgnoreCase("TRADEROUTEAPP"))
        {
            if (!TRADEROUTEAPP_Done)
            {
                String val = getStringValue(ch, start);
                
                if (transObject != null)
                {
                    transObject.setTradeFormApp(val);
                }
                
                currElementName = "";
                TRADEROUTEAPP_Done = true;
            }
        }
        
        if (currElementName.equalsIgnoreCase("FILENAME"))
        {
            if (!FILENAME_Done)
            {
                String val = getStringValue(ch, start);
                
                if (transObject != null)
                {
                    transObject.setFileName(val);
                }
                
                currElementName = "";
                FILENAME_Done = true;
            }
        }  
        
        if (currElementName.equalsIgnoreCase("RefNum"))
        {
            if (!OrderNumber_Done)
            {
                String val = getStringValue(ch, start);
                
                if (transObject != null)
                {
                    transObject.setDocID(val);
                }
                
                currElementName = "";
                OrderNumber_Done = true;
            }
        }     
        
        
        if(currElementName.equalsIgnoreCase("ENGLISHNAME"))
        {
            String val = getStringValue(ch, start);
            
            if(val.equalsIgnoreCase("General Note"))
            {
                GeneralNote_Found = true;
            }
        }
        
        if(GeneralNote_Found && currElementName.equalsIgnoreCase("RESOLVEDVALUE"))
        {
            if(!GeneralNote_Done)
            {
                String val = getStringValue(ch, start);
                
                if (transObject != null)
                {
                    //log.info("Found General Note Res Val: "+val);
                    transObject.setGeneralNote(val);
                }
                
                currElementName = "";
                GeneralNote_Done = true;
                GeneralNote_Found = false;
            }                 
        }
        
    }
    
    
    /** get the value out of a char array */
    private String getStringValue(char ch[], int start)
    {
        try{
            String newST = new String(ch);
            newST = newST.substring(start);
            int pos = newST.indexOf("<");
            String val = newST.substring(0, pos);
            return val.trim();
        }catch(Exception e)
        {
            /** doesn't matter is exception occurrs, just continue */
        }
        return "";
    }
    
    /** Ignorable whitespace. */
    public void ignorableWhitespace(char ch[], int start, int length)
    throws SAXException
    {        
        characters(ch, start, length);
        fOut.flush();        
    }
    
    /** End element. */
    public void endElement(String uri, String local, String raw)
    throws SAXException
    {        
        fElementDepth--;        
    }
    
    /** Warning. */
    public void warning(SAXParseException ex) throws SAXException
    {
        printError("Warning", ex);
    } // warning(SAXParseException)
    
    /** Error. */
    public void error(SAXParseException ex) throws SAXException
    {
        printError("Error", ex);
    } // error(SAXParseException)
    
    /** Fatal error. */
    public void fatalError(SAXParseException ex) throws SAXException
    {
        printError("Fatal Error", ex);
        throw ex;
    } // fatalError(SAXParseException)
    
      
    /** Start DTD. */
    public void startDTD(String name, String publicId, String systemId)
    throws SAXException
    {
    } // startDTD(String,String,String)
    
    /** End DTD. */
    public void endDTD() throws SAXException
    {
    } // endDTD()
    
    /** Start entity. */
    public void startEntity(String name) throws SAXException
    {
    } // startEntity(String)
    
    /** End entity. */
    public void endEntity(String name) throws SAXException
    {
    } // endEntity(String)
    
    /** Start CDATA section. */
    public void startCDATA() throws SAXException
    {
    } // startCDATA()
    
    /** End CDATA section. */
    public void endCDATA() throws SAXException
    {
    } // endCDATA()
    
    /** Comment. */
    public void comment(char ch[], int start, int length) throws SAXException
    {
        if (!fCanonical && fElementDepth > 0)
        {
            fOut.print("<!--");
            normalizeAndPrint(ch, start, length);
            fOut.print("-->");
            fOut.flush();
        }
    }
    
    /** Returns a sorted list of attributes. */
    /**protected Attributes sortAttributes(Attributes attrs)
    {        
        AttributesImpl attributes = new AttributesImpl();
        
        int len = (attrs != null) ? attrs.getLength() : 0;
        for (int i = 0; i < len; i++)
        {
            String name = attrs.getQName(i);
            int count = attributes.getLength();
            int j = 0;
            while (j < count)
            {
                if (name.compareTo(attributes.getQName(j)) < 0)
                {
                    break;
                }
                j++;
            }
            attributes.insertAttributeAt(j, name, attrs.getType(i),
            attrs.getValue(i));
        }
        
        return attributes;        
    }*/
    
    /** Normalizes and prints the given string. */
    protected void normalizeAndPrint(String s)
    {
        
        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++)
        {
            char c = s.charAt(i);
            normalizeAndPrint(c);
        }
        
    }
    
    /** Normalizes and prints the given array of characters. */
    protected void normalizeAndPrint(char[] ch, int offset, int length)
    {
        for (int i = 0; i < length; i++)
        {
            normalizeAndPrint(ch[offset + i]);
        }
    }
    
    /** Normalizes and print the given character. */
    protected void normalizeAndPrint(char c)
    {        
        switch (c)
        {
            case '<':
            {
                fOut.print("&lt;");
                break;
            }
            case '>':
            {
                fOut.print("&gt;");
                break;
            }
            case '&':
            {
                fOut.print("&amp;");
                break;
            }
            case '"':
            {
                fOut.print("&quot;");
                break;
            }
            case '\r':
            case '\n':
            {
                if (fCanonical)
                {
                    fOut.print("&#");
                    fOut.print(Integer.toString(c));
                    fOut.print(';');
                    break;
                }
            }
            default:
            {
                fOut.print(c);
            }
        }
   }
    
    /** Prints the error message. */
    protected void printError(String type, SAXParseException ex)
    {        
        System.err.print("[");
        System.err.print(type);
        System.err.print("] ");
        String systemId = ex.getSystemId();
        if (systemId != null)
        {
            int index = systemId.lastIndexOf('/');
            if (index != -1)
                systemId = systemId.substring(index + 1);
            System.err.print(systemId);
        }
        System.err.print(':');
        System.err.print(ex.getLineNumber());
        System.err.print(':');
        System.err.print(ex.getColumnNumber());
        System.err.print(": ");
        System.err.print(ex.getMessage());
        System.err.println();
        System.err.flush();        
    } 
    
    
    
    public TransactionObject readDocumentValues(String arg)
    {
        /** parse the document */
        try
        {
            //log.info("File:"+arg);
            transObject = null;
            transObject = new TransactionObject();
            //java.net.URL url = new java.net.URL(arg);
            //org.xml.sax.InputSource is = new org.xml.sax.InputSource("file://"+arg);
            parser.parse(arg);            
            
            //parser.parse(is);
        }
        catch (SAXParseException e)
        {
//            System.err.println("SAXParseException");
//            e.printStackTrace();
        }
        catch(SAXException se)
        {
//            System.err.println("SAXException");
//            se.printStackTrace();
        }
        catch (Exception e)
        {
            System.err.println("error: Parse error occurred - "+e.getMessage());
            if (e instanceof SAXException)
            {
                e = ((SAXException)e).getException();
            }
            e.printStackTrace(System.err);
        }
        
        TRANSACTIONTYPE_Done = false;
        DOCCREATORTPID_Done = false;
        CREATIONDATE_Done = false;
        USERLIST_Done = false;
        TRANSACTIONCENTREDESC_Done = false;
        TRADEFORMSTATUS_Done = false;
        TRADEROUTEAPP_Done = false;
        FILENAME_Done = false;
        OrderNumber_Done = false;
        GeneralNote_Found = false;
        GeneralNote_Done = false;
        
        return transObject;
    }
    
    public void initialise()
    {        
        // variables        
        boolean namespaces = DEFAULT_NAMESPACES;
        boolean validation = DEFAULT_VALIDATION;
        boolean schemaValidation = DEFAULT_SCHEMA_VALIDATION;
        boolean schemaFullChecking = DEFAULT_SCHEMA_FULL_CHECKING;
        boolean canonical = DEFAULT_CANONICAL;        
        
        /** use all the default settings */
        try
        {
            /** create the parser */
            parser = XMLReaderFactory.createXMLReader(DEFAULT_PARSER_NAME);
            
            /** set features */
            
            parser.setFeature(NAMESPACES_FEATURE_ID, namespaces);
            parser.setFeature(VALIDATION_FEATURE_ID, validation);
            parser.setFeature(SCHEMA_VALIDATION_FEATURE_ID, schemaValidation);            
            
            /** create the writer */
            //writer = new SAXReader();
            this.setOutput(System.out, "UTF8");
            
            /** set handlers */
            parser.setContentHandler(this);
            parser.setErrorHandler(this);
            
            parser.setProperty(LEXICAL_HANDLER_PROPERTY_ID, this);
            
            this.setCanonical(canonical);            
        }
        catch(Exception e)
        {
            log.info("Exception Initialising Parser: ");
            e.printStackTrace();
        }
    }
    
    /** Main program entry point. */
    public static void main(String argv[])
    {        
        SAXTransactionReaderExport w = new SAXTransactionReaderExport();
        w.initialise();
        
        java.io.File dir = new java.io.File("C:\\TradeRoute\\TRANSACTIONS\\traderoute_queue\\Export_Documents");
        
        java.io.File[] fileList = dir.listFiles();
        
        java.util.Date now = new java.util.Date();        
        
        System.out.println("Starting...Files("+fileList.length+").");
        System.out.println("Start Time: "+now);
        
        for(int i = 0; i < fileList.length; i++)
        {
            java.io.File currFile = fileList[i];
            
            TransactionObject tc = w.readDocumentValues(currFile.toURI().toString());
            
            tc.print();
            
        }
        java.util.Date now2 = new java.util.Date();
        System.out.println("End Time: "+now2);
        System.out.println("Finished...");
        
    } // main(String[])
    
    //
    // Private static methods
    //
    
    /** Prints the usage. */
    private static void printUsage()
    {
        
        System.err.println("usage: java sax.SAXReader (options) uri ...");
        System.err.println();
        
        System.err.println("options:");
        System.err.println("  -p name  Select parser by name.");
        System.err.println("  -n | -N  Turn on/off namespace processing.");
        System.err.println("  -v | -V  Turn on/off validation.");
        System.err.println("  -s | -S  Turn on/off Schema validation support.");
        System.err.println("           NOTE: Not supported by all parsers.");
        System.err.println("  -f  | -F Turn on/off Schema full checking.");
        System.err.println("           NOTE: Requires use of -s and not supported by all parsers.");
        System.err.println("  -c | -C  Turn on/off Canonical XML output.");
        System.err.println("           NOTE: This is not W3C canonical output.");
        System.err.println("  -h       This help screen.");
        System.err.println();
        
        System.err.println("defaults:");
        System.err.println("  Parser:     "+DEFAULT_PARSER_NAME);
        System.err.print("  Namespaces: ");
        System.err.println(DEFAULT_NAMESPACES ? "on" : "off");
        System.err.print("  Validation: ");
        System.err.println(DEFAULT_VALIDATION ? "on" : "off");
        System.err.print("  Schema:     ");
        System.err.println(DEFAULT_SCHEMA_VALIDATION ? "on" : "off");
        System.err.print("  Schema full checking:     ");
        System.err.println(DEFAULT_SCHEMA_FULL_CHECKING ? "on" : "off");
        System.err.print("  Canonical:  ");
        System.err.println(DEFAULT_CANONICAL ? "on" : "off");
        
    } // printUsage()
    
} // class SAXReader
