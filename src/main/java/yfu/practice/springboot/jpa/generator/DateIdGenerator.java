package yfu.practice.springboot.jpa.generator;

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
        
        this.prefixWord = params.getProperty(PREFIX_WORD, "C");
        this.seqFormat = params.getProperty(SEQ_FORMAT, "%03d");
        this.dateFormat = DateTimeFormatter.ofPattern(params.getProperty(DATE_FORMAT, "yyMMdd"));
    }
    
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return new StringBuilder()
                .append(this.prefixWord)
                .append(LocalDate.now().format(this.dateFormat))
                .append(String.format(this.seqFormat, super.generate(session, object)))
                .toString();
    }
}