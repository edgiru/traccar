package org.traccar.protocol;

import org.jboss.netty.buffer.ChannelBuffers;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class Gt06ProtocolDecoderTest {
    
    @Test
    public void testDecode() throws Exception {
        
        Gt06ProtocolDecoder decoder = new Gt06ProtocolDecoder(new TestDataManager());
        
        byte[] buf1 = {0x78,0x78,0x1F,0x12,0x0B,0x08,0x1D,0x11,0x2E,0x10,(byte)0xCC,0x02,0x7A,(byte)0xC7,(byte)0xEB,0x0C,0x46,0x58,0x49,0x00,0x14,(byte)0x8F,0x01,(byte)0xCC,0x00,0x28,0x7D,0x00,0x1F,(byte)0xB8,0x00,0x03,(byte)0x80,(byte)0x81,0x0D,0x0A};
        assertNotNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf1)));
        
        byte[] buf2 = {0x78,0x78,0x0D,0x01,0x08,0x64,0x71,0x70,0x03,0x28,0x35,(byte)0x81,0x00,0x09,0x3F,0x04,0x0D,0x0A};
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf2)));

    }

}