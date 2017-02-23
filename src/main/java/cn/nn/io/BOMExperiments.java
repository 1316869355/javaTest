package cn.nn.io;

/*
 * [BOMExperiments.java]
 *
 * Summary: Experiments with Byte Order Marks.
 *
 * Copyright: (c) 2016 Roedy Green, Canadian Mind Products, http://mindprod.com
 *
 * Licence: This software may be copied and used freely for any purpose but military.
 *          http://mindprod.com/contact/nonmil.html
 *
 * Requires: JDK 1.8+
 *
 * Created with: JetBrains IntelliJ IDEA IDE http://www.jetbrains.com/idea/
 *
 * Version History:
 *  1.0 2016-07-18 initial version
 */
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.io.PrintWriter;

        import static java.lang.System.*;

/**
 * Experiments with Byte Order Marks.
 *
 * @author Roedy Green, Canadian Mind Products
 * @version 1.0 2016-07-18 initial version
 * @since 2016-07-18
 */
public class BOMExperiments
{
    /**
     * a byte order mark
     */
    static final char BOM = ( char ) 0xfeff;

    /**
     * dump chars just read in hex
     *
     * @param charsRead count of chars read
     * @param ca        array of chars read
     */
    public static void dumpCa( int charsRead, char[] ca )
    {
        out.println( charsRead
                + " : "
                + Integer.toHexString( ca[ 0 ] )
                + ", "
                + Integer.toHexString( ca[ 1 ] )
                + ", "
                + Integer.toHexString( ca[ 2 ] )
                + ", "
                + Integer.toHexString( ca[ 3 ] ) );
    }

    /**
     * write and read with various combination of encoding and BOM
     *
     * @param args not used
     */
    public static void main( final String[] args )
    {
        try
        {
            {
                // prepare a UTF-8 file
                // O P E N
                final FileOutputStream fos = new FileOutputStream( "D:/temp/utf8.txt", false /* append */ );
                final OutputStreamWriter eosw = new OutputStreamWriter( fos, "UTF-8" );
                final PrintWriter prw = new PrintWriter( eosw, false /* auto flush on println */ );
                // W R I T E
                prw.write( BOM );
                prw.write( "platypus" );
                // C L O S E
                prw.close();
                // generates file with EF BB EF BOM
            }
            {
                // prepare a UTF-16BE file
                // O P E N
                final FileOutputStream fos = new FileOutputStream( "D:/temp/utf16be.txt", false /* append */ );
                final OutputStreamWriter eosw = new OutputStreamWriter( fos, "UTF-16BE" );
                final PrintWriter prw = new PrintWriter( eosw, false /* auto flush on println */ );
                // W R I T E
                prw.write( BOM );
                prw.write( "platypus" );
                // C L O S E
                prw.close();
                // generates file with FE FF BOM
            }
            {
                // prepare a UTF-16LE file
                // O P E N
                final FileOutputStream fos = new FileOutputStream( "D:/temp/utf16le.txt", false /* append */ );
                final OutputStreamWriter eosw = new OutputStreamWriter( fos, "UTF-16LE" );
                final PrintWriter prw = new PrintWriter( eosw, false /* auto flush on println */ );
                // W R I T E
                prw.write( BOM );
                prw.write( "platypus" );
                // C L O S E
                prw.close();
                // generates file with FE FF BOM
            }
            {
                // Read UTF-8
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf8.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-8" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // first char is feff BOM
            }
            {
                // Read UTF-16BE
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf16be.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-16BE" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // first char is feff BOM
            }
            {
                // Read UTF-16LE
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf16le.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-16LE" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // first char is feff BOM
            }
            {
                // Read UTF-8 mismatched
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf16be.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-8" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // first two chars are fffd fffd  then 0 70, a mess
            }
            {
                // Read UTF-8 mismatched
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf16le.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-8" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // first two chars are fffd fffd  then 70 0, a mess
            }
            {
                // Read UTF-BE mismatched
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf8.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-16BE" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // chars are efbb, bf70, 6c61, 7479, a mess
            }
            {
                // Read UTF-BE mismatched
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf16le.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-16BE" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // chars are fffd, 7000, 6c00, 6100, a mess
            }
            {
                // Read UTF-EE mismatched
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf8.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-16LE" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // chars are: bbef, 70bf, 616c, 7974 , a mess
            }
            {
                // Read UTF-LE mismatched
                // O P E N
                final FileInputStream fis = new FileInputStream( "D:/temp/utf16be.txt" );
                final InputStreamReader eisr = new InputStreamReader( fis, "UTF-16LE" );
                // R E A D
                final char[] ca = new char[ 9 ];
                final int charsRead = eisr.read( ca );
                dumpCa( charsRead, ca );
                // C L O S E
                eisr.close();
                // chars are: fffd, 7000, 6c00, 6100, a mess
            }
        }
        catch ( IOException e )
        {
            err.println( e.getMessage() );
        }
    }
}