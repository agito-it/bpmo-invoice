package org.agito.bpmo.sample.invoice;


import de.agito.cps.core.bpmo.IAttachmentEntry;
import de.agito.cps.core.bpmo.IKeywordEntry;
import de.agito.cps.core.bpmo.INodeElement;
import de.agito.cps.core.bpmo.INodeElementKey;
import de.agito.cps.core.bpmo.IPrincipalValue;
import de.agito.cps.core.bpmo.IRow;
import de.agito.cps.core.bpmo.PrincipalType;
import de.agito.cps.core.bpmo.api.access.BPMOAccess;
import de.agito.cps.core.bpmo.api.access.CellAccess;
import de.agito.cps.core.bpmo.api.access.CharacteristicAccess;
import de.agito.cps.core.bpmo.api.access.NodeKeyAccess;
import de.agito.cps.core.bpmo.api.access.RowAccess;
import de.agito.cps.core.bpmo.api.access.TableAccessCurrent;
import java.io.InputStream;
import java.lang.Boolean;
import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * BPMOAccess for Invoice.
 * 
 * @author Jörg Burmeister
 */
public final class InvoiceAccess extends BPMOAccess<InvoiceAccess> {

	public InvoiceAccess(INodeElement context) { super(context); }

	/**
	 * <b>Invoice Root</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public Invoice getInvoice() { return super.<Invoice>getKeyAccess(Invoice.class, "Invoice$Invoice"); }
	/**
	 * <b>Invoice copy</b>
	 * <p>
	 * Digital copy of invoice as PDF file
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ATTACHMENT}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public InvoiceAttachment getInvoiceAttachment() { return super.<InvoiceAttachment>getCharacteristicAccess(InvoiceAttachment.class, "Invoice$InvoiceAttachment"); }
	/**
	 * <b>Invoicing party</b>
	 * <p>
	 * Name of party sending the invoice
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public InvoicingParty getInvoicingParty() { return super.<InvoicingParty>getCharacteristicAccess(InvoicingParty.class, "Invoice$InvoicingParty"); }
	/**
	 * <b>Invoice number</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public InvoiceNumber getInvoiceNumber() { return super.<InvoiceNumber>getCharacteristicAccess(InvoiceNumber.class, "Invoice$InvoiceNumber"); }
	/**
	 * <b>Invoice date</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType DATE}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public InvoiceDate getInvoiceDate() { return super.<InvoiceDate>getCharacteristicAccess(InvoiceDate.class, "Invoice$InvoiceDate"); }
	/**
	 * <b>Invoice received</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType DATE}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public InvoiceReceived getInvoiceReceived() { return super.<InvoiceReceived>getCharacteristicAccess(InvoiceReceived.class, "Invoice$InvoiceReceived"); }
	/**
	 * <b>Term of payment (Days)</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public TermOfPayment getTermOfPayment() { return super.<TermOfPayment>getCharacteristicAccess(TermOfPayment.class, "Invoice$TermOfPayment"); }
	/**
	 * <b>Order number</b>
	 * <p>
	 * Please enter if exist on invoice order
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public OrderNumber getOrderNumber() { return super.<OrderNumber>getCharacteristicAccess(OrderNumber.class, "Invoice$OrderNumber"); }
	/**
	 * <b>Profitcenter</b>
	 * <p>
	 * Profitcenter in charge for this invoice
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public OrderProfitcenter getOrderProfitcenter() { return super.<OrderProfitcenter>getCharacteristicAccess(OrderProfitcenter.class, "Invoice$OrderProfitcenter"); }
	/**
	 * <b>Cost center</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public OrderCostCenter getOrderCostCenter() { return super.<OrderCostCenter>getCharacteristicAccess(OrderCostCenter.class, "Invoice$OrderCostCenter"); }
	/**
	 * <b>Responsible</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType PRINCIPAL}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public Approver getApprover() { return super.<Approver>getCharacteristicAccess(Approver.class, "Invoice$Approver"); }
	/**
	 * <b>Order checked?</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType BOOLEAN}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public OrderChecked getOrderChecked() { return super.<OrderChecked>getCharacteristicAccess(OrderChecked.class, "Invoice$OrderChecked"); }
	/**
	 * <b>Shipment checked?</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType BOOLEAN}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public ShipmentChecked getShipmentChecked() { return super.<ShipmentChecked>getCharacteristicAccess(ShipmentChecked.class, "Invoice$ShipmentChecked"); }
	/**
	 * <b>Position per tax rate</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType TABLE_DEFINITION}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	public TaxPositions getTaxPositions() { return super.<TaxPositions>getTableAccessCV(TaxPositions.class, "Invoice$TaxPositions"); }

	@SuppressWarnings("unchecked")
	public final static class Invoice extends NodeKeyAccess {
		protected Invoice(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		protected Invoice(BPMOAccess<?> bpmoAccess, String path, INodeElementKey nodeElementKey, Object value) { super(bpmoAccess, path, nodeElementKey, value); }
		public String getValue() { return super.<String>getValue(); }
	}

	public final static class InvoiceAttachment extends CharacteristicAccess {
		protected InvoiceAttachment(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public IAttachmentEntry getValue() { return super.<IAttachmentEntry>getCurrentValue(); }
		public void setValue(IAttachmentEntry value) { super.setCurrentValue(value); }
		public void setValue(String fileName, String fileMimeType, byte[] fileContent) { super.setCurrentAttachmentValue(fileName, fileMimeType, fileContent); }
		public void setValue(String fileName, String fileMimeType, InputStream fileContent) { super.setCurrentAttachmentValue(fileName, fileMimeType, fileContent); }
	}

	public final static class InvoicingParty extends CharacteristicAccess {
		protected InvoicingParty(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getValue() { return super.<String>getCurrentValue(); }
		public void setValue(String value) { super.setCurrentValue(value); }
	}

	public final static class InvoiceNumber extends CharacteristicAccess {
		protected InvoiceNumber(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getValue() { return super.<String>getCurrentValue(); }
		public void setValue(String value) { super.setCurrentValue(value); }
	}

	public final static class InvoiceDate extends CharacteristicAccess {
		protected InvoiceDate(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public XMLGregorianCalendar getValue() { return super.<XMLGregorianCalendar>getCurrentValue(); }
		public void setValue(XMLGregorianCalendar value) { super.setCurrentValue(value); }
	}

	public final static class InvoiceReceived extends CharacteristicAccess {
		protected InvoiceReceived(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public XMLGregorianCalendar getValue() { return super.<XMLGregorianCalendar>getCurrentValue(); }
		public void setValue(XMLGregorianCalendar value) { super.setCurrentValue(value); }
	}

	public final static class TermOfPayment extends CharacteristicAccess {
		protected TermOfPayment(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public BigDecimal getValue() { return super.<BigDecimal>getCurrentValue(); }
		public void setValue(BigDecimal value) { super.setCurrentValue(value); }
	}

	public final static class OrderNumber extends CharacteristicAccess {
		protected OrderNumber(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public String getValue() { return super.<String>getCurrentValue(); }
		public void setValue(String value) { super.setCurrentValue(value); }
	}

	public final static class OrderProfitcenter extends CharacteristicAccess {
		protected OrderProfitcenter(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public IKeywordEntry getValue() { return super.<IKeywordEntry>getCurrentValue(); }
		public String getValueKey() { return super.getCurrentValueKey(); }
		public void setValue(String value) { super.setCurrentValue(value); }
	}

	public final static class OrderCostCenter extends CharacteristicAccess {
		protected OrderCostCenter(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public IKeywordEntry getValue() { return super.<IKeywordEntry>getCurrentValue(); }
		public String getValueKey() { return super.getCurrentValueKey(); }
		public void setValue(String value) { super.setCurrentValue(value); }
	}

	public final static class Approver extends CharacteristicAccess {
		protected Approver(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public IPrincipalValue getValue() { return super.<IPrincipalValue>getCurrentValue(); }
		public void setValue(IPrincipalValue value) { super.setCurrentValue(value); }
		public void setValue(String principalId, PrincipalType principalType) { super.setCurrentPrincipalValue(principalId, principalType); }
	}

	public final static class OrderChecked extends CharacteristicAccess {
		protected OrderChecked(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public Boolean getValue() { return super.<Boolean>getCurrentValue(); }
		public void setValue(Boolean value) { super.setCurrentValue(value); }
	}

	public final static class ShipmentChecked extends CharacteristicAccess {
		protected ShipmentChecked(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }
		public Boolean getValue() { return super.<Boolean>getCurrentValue(); }
		public void setValue(Boolean value) { super.setCurrentValue(value); }
	}

	public final static class TaxPositions extends TableAccessCurrent<TaxPositions.Row> {
		protected TaxPositions(BPMOAccess<?> bpmoAccess, String path) { super(bpmoAccess, path); }

		public final static class Row extends RowAccess {
			protected Row(BPMOAccess<?> bpmoAccess, IRow row) { super(bpmoAccess, row); }
			public NetAmount getNetAmount() { return super.<NetAmount>getCellAccess(NetAmount.class, "Invoice$TaxPositions$NetAmount", "NetAmount"); }
			public TaxRate getTaxRate() { return super.<TaxRate>getCellAccess(TaxRate.class, "Invoice$TaxPositions$TaxRate", "TaxRate"); }
			public TaxAmount getTaxAmount() { return super.<TaxAmount>getCellAccess(TaxAmount.class, "Invoice$TaxPositions$TaxAmount", "TaxAmount"); }
			public TotalAmount getTotalAmount() { return super.<TotalAmount>getCellAccess(TotalAmount.class, "Invoice$TaxPositions$TotalAmount", "TotalAmount"); }
		}

		@SuppressWarnings("unchecked")
		public final static class NetAmount extends CellAccess {
			protected NetAmount(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
			public BigDecimal getValue() { return super.<BigDecimal>getValue(); }
			public void setValue(BigDecimal value) { super.setValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class TaxRate extends CellAccess {
			protected TaxRate(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
			public IKeywordEntry getValue() { return super.<IKeywordEntry>getValue(); }
			public String getValueKey() { return super.getValueKey(); }
			public void setValue(String value) { super.setValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class TaxAmount extends CellAccess {
			protected TaxAmount(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
			public BigDecimal getValue() { return super.<BigDecimal>getValue(); }
			public void setValue(BigDecimal value) { super.setValue(value); }
		}

		@SuppressWarnings("unchecked")
		public final static class TotalAmount extends CellAccess {
			protected TotalAmount(BPMOAccess<?> bpmoAccess, RowAccess rowAccess, String path, String id) { super(bpmoAccess, rowAccess, path, id); }
			public BigDecimal getValue() { return super.<BigDecimal>getValue(); }
			public void setValue(BigDecimal value) { super.setValue(value); }
		}
	}

}
