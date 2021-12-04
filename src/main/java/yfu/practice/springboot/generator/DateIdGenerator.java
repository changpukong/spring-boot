package yfu.practice.springboot.generator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 * 自訂自增主鍵
 * @author yfu
 */
public class DateIdGenerator extends SequenceStyleGenerator {

    public static final String PREFIX_WORD = "prefixWord";
    
    public static final String SEQ_FORMAT = "seqFormat";

    public static final String DATE_FORMAT = "dateFormat";
    
    private String prefixWord;
    
    private String seqFormat;
    
    private DateTimeFormatter dateFormat;
    
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        // type為Entity屬性的型別，但SEQUENCE應該為Long
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        
        prefixWord = params.getProperty(PREFIX_WORD, "C");
        seqFormat = params.getProperty(SEQ_FORMAT, "%03d");
        dateFormat = DateTimeFormatter.ofPattern(params.getProperty(DATE_FORMAT, "yyMMdd"));
    }
    
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return new StringBuilder()
                .append(prefixWord)
                .append(LocalDate.now().format(dateFormat))
                .append(String.format(seqFormat, super.generate(session, object)))
                .toString();
    }
}