package br.gov.pa.prodepa.pae.documento.mapper;

import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.Especie;
import br.gov.pa.prodepa.pae.documento.domain.model.Margem;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstrutura;
import br.gov.pa.prodepa.pae.documento.domain.model.Orgao;
import br.gov.pa.prodepa.pae.documento.entity.EspecieEntity;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;
import br.gov.pa.prodepa.pae.documento.entity.OrgaoEntity;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-24T19:10:59-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class ModeloEstruturaMapperImpl implements ModeloEstruturaMapper {

    @Override
    public ModeloEstruturaEntity mapToEntity(ModeloEstrutura modeloEstrutura) {
        if ( modeloEstrutura == null ) {
            return null;
        }

        ModeloEstruturaEntity modeloEstruturaEntity = new ModeloEstruturaEntity();

        modeloEstruturaEntity.setMargemDireita( modeloEstruturaMargensDireita( modeloEstrutura ) );
        modeloEstruturaEntity.setMargemEsquerda( modeloEstruturaMargensEsquerda( modeloEstrutura ) );
        modeloEstruturaEntity.setMargemTopo( modeloEstruturaMargensTopo( modeloEstrutura ) );
        modeloEstruturaEntity.setMargemRodape( modeloEstruturaMargensRodape( modeloEstrutura ) );
        modeloEstruturaEntity.setManutUsuarioId( modeloEstruturaAuditoriaManutUsuarioId( modeloEstrutura ) );
        modeloEstruturaEntity.setId( modeloEstrutura.getId() );
        modeloEstruturaEntity.setCabecalho( modeloEstrutura.getCabecalho() );
        modeloEstruturaEntity.setRodape( modeloEstrutura.getRodape() );
        modeloEstruturaEntity.setTitulo( modeloEstrutura.getTitulo() );
        byte[] thumbnail = modeloEstrutura.getThumbnail();
        if ( thumbnail != null ) {
            modeloEstruturaEntity.setThumbnail( Arrays.copyOf( thumbnail, thumbnail.length ) );
        }
        if ( modeloEstrutura.getAtivo() != null ) {
            modeloEstruturaEntity.setAtivo( modeloEstrutura.getAtivo() );
        }
        modeloEstruturaEntity.setFormato( modeloEstrutura.getFormato() );
        modeloEstruturaEntity.setOrientacao( modeloEstrutura.getOrientacao() );
        modeloEstruturaEntity.setEspecies( especieSetToEspecieEntitySet( modeloEstrutura.getEspecies() ) );
        modeloEstruturaEntity.setOrgao( orgaoToOrgaoEntity( modeloEstrutura.getOrgao() ) );

        return modeloEstruturaEntity;
    }

    private String modeloEstruturaMargensDireita(ModeloEstrutura modeloEstrutura) {
        if ( modeloEstrutura == null ) {
            return null;
        }
        Margem margens = modeloEstrutura.getMargens();
        if ( margens == null ) {
            return null;
        }
        String direita = margens.getDireita();
        if ( direita == null ) {
            return null;
        }
        return direita;
    }

    private String modeloEstruturaMargensEsquerda(ModeloEstrutura modeloEstrutura) {
        if ( modeloEstrutura == null ) {
            return null;
        }
        Margem margens = modeloEstrutura.getMargens();
        if ( margens == null ) {
            return null;
        }
        String esquerda = margens.getEsquerda();
        if ( esquerda == null ) {
            return null;
        }
        return esquerda;
    }

    private String modeloEstruturaMargensTopo(ModeloEstrutura modeloEstrutura) {
        if ( modeloEstrutura == null ) {
            return null;
        }
        Margem margens = modeloEstrutura.getMargens();
        if ( margens == null ) {
            return null;
        }
        String topo = margens.getTopo();
        if ( topo == null ) {
            return null;
        }
        return topo;
    }

    private String modeloEstruturaMargensRodape(ModeloEstrutura modeloEstrutura) {
        if ( modeloEstrutura == null ) {
            return null;
        }
        Margem margens = modeloEstrutura.getMargens();
        if ( margens == null ) {
            return null;
        }
        String rodape = margens.getRodape();
        if ( rodape == null ) {
            return null;
        }
        return rodape;
    }

    private Long modeloEstruturaAuditoriaManutUsuarioId(ModeloEstrutura modeloEstrutura) {
        if ( modeloEstrutura == null ) {
            return null;
        }
        Auditoria auditoria = modeloEstrutura.getAuditoria();
        if ( auditoria == null ) {
            return null;
        }
        Long manutUsuarioId = auditoria.getManutUsuarioId();
        if ( manutUsuarioId == null ) {
            return null;
        }
        return manutUsuarioId;
    }

    protected EspecieEntity especieToEspecieEntity(Especie especie) {
        if ( especie == null ) {
            return null;
        }

        EspecieEntity especieEntity = new EspecieEntity();

        especieEntity.setId( especie.getId() );
        especieEntity.setNome( especie.getNome() );

        return especieEntity;
    }

    protected Set<EspecieEntity> especieSetToEspecieEntitySet(Set<Especie> set) {
        if ( set == null ) {
            return null;
        }

        Set<EspecieEntity> set1 = new HashSet<EspecieEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Especie especie : set ) {
            set1.add( especieToEspecieEntity( especie ) );
        }

        return set1;
    }

    protected OrgaoEntity orgaoToOrgaoEntity(Orgao orgao) {
        if ( orgao == null ) {
            return null;
        }

        OrgaoEntity orgaoEntity = new OrgaoEntity();

        orgaoEntity.setId( orgao.getId() );

        return orgaoEntity;
    }
}
