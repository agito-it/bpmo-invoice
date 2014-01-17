package org.agito.bpmo.sample.invoice;


import de.agito.cps.core.bpmo.ControlType;
import de.agito.cps.core.bpmo.DataTypeFactory;
import de.agito.cps.core.bpmo.IEnumInspector;
import de.agito.cps.core.bpmo.api.enums.IBODataElement;
import de.agito.cps.core.bpmo.api.enums.IBOId;
import de.agito.cps.core.bpmo.api.enums.IBONode;


/**
 * Enum for Invoice.
 *
 * @author Jörg Burmeister
 */
public enum Invoice implements IBODataElement {

	/**
	 * <b>Invoice copy</b>
	 * <p>
	 * Digital copy of invoice as PDF file
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ATTACHMENT}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	InvoiceAttachment("InvoiceAttachment", "Invoice$InvoiceAttachment", ControlType.INTERACTIVE),

	/**
	 * <b>Invoicing party</b>
	 * <p>
	 * Name of party sending the invoice
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	InvoicingParty("InvoicingParty", "Invoice$InvoicingParty", ControlType.INTERACTIVE),

	/**
	 * <b>Invoice number</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	InvoiceNumber("InvoiceNumber", "Invoice$InvoiceNumber", ControlType.INTERACTIVE),

	/**
	 * <b>Invoice date</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType DATE}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	InvoiceDate("InvoiceDate", "Invoice$InvoiceDate", ControlType.INTERACTIVE),

	/**
	 * <b>Invoice received</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType DATE}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	InvoiceReceived("InvoiceReceived", "Invoice$InvoiceReceived", ControlType.INTERACTIVE),

	/**
	 * <b>Term of payment (Days)</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	TermOfPayment("TermOfPayment", "Invoice$TermOfPayment", ControlType.INTERACTIVE),

	/**
	 * <b>Position per tax rate</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType TABLE_DEFINITION}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	TaxPositions("TaxPositions", "Invoice$TaxPositions", ControlType.INTERACTIVE),

	/**
	 * <b>Net amount €</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	TaxPositions$NetAmount("NetAmount", "Invoice$TaxPositions$NetAmount", ControlType.INTERACTIVE),

	/**
	 * <b>Tax rate</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	TaxPositions$TaxRate("TaxRate", "Invoice$TaxPositions$TaxRate", ControlType.INTERACTIVE),

	/**
	 * <b>Tax amount €</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TaxPositions$TaxAmount("TaxAmount", "Invoice$TaxPositions$TaxAmount", ControlType.DEFAULT),

	/**
	 * <b>Gross total €</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TaxPositions$TotalAmount("TotalAmount", "Invoice$TaxPositions$TotalAmount", ControlType.DEFAULT),

	/**
	 * <b>Order number</b>
	 * <p>
	 * Please enter if exist on invoice order
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	OrderNumber("OrderNumber", "Invoice$OrderNumber", ControlType.INTERACTIVE),

	/**
	 * <b>Profitcenter</b>
	 * <p>
	 * Profitcenter in charge for this invoice
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	OrderProfitcenter("OrderProfitcenter", "Invoice$OrderProfitcenter", ControlType.INTERACTIVE),

	/**
	 * <b>Cost center</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	OrderCostCenter("OrderCostCenter", "Invoice$OrderCostCenter", ControlType.INTERACTIVE),

	/**
	 * <b>Responsible</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType PRINCIPAL}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	Approver("Approver", "Invoice$Approver", ControlType.INTERACTIVE),

	/**
	 * <b>Order checked?</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType BOOLEAN}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	OrderChecked("OrderChecked", "Invoice$OrderChecked", ControlType.INTERACTIVE),

	/**
	 * <b>Shipment checked?</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType BOOLEAN}<i><br>
	 * <i>{@link ControlType INTERACTIVE}<i><br>
	 */
	ShipmentChecked("ShipmentChecked", "Invoice$ShipmentChecked", ControlType.INTERACTIVE);

	/**
	 * <b>BPMO Identifier</b>
	 */
	public final static IBOId $BPMO = new IBOId.BOId("Invoice");

	/**
	 * <b>Node Identifier</b>
	 */
	public final static IBONode $ID = new IBONode.BONode("Invoice", "Invoice", ControlType.DEFAULT);

	private final static IEnumInspector ENUM_INSPECTOR = DataTypeFactory.getInstance().createEnumInspector(Invoice.class);
	private Invoice(String id, String path, ControlType controlType) { this.id = id; this.path = path; this.controlType = controlType; }
	private String id;
	public String getId() { return id; }
	private String path;
	public String getPath() { return path; }
	private ControlType controlType;
	public ControlType getControlType() { return controlType; }

	/**
	 * Retrieve a content element for a given path.
	 * 
	 * @return
	 *             Element requested or {@link IEnumInspector}.UNDEFINED_DATA if element does not exist. 
	 * @throws ClassCastException
	 *             when the corresponding element for given path is not a content element.
	 */
	public static IBODataElement getIBODataElementByPath(String path) throws ClassCastException { return ENUM_INSPECTOR.getIBODataElementByPath(path); }

	/**
	 * Retrieve a node for a given path.
	 * 
	 * @return
	 *             Node requested or {@link IEnumInspector}.UNDEFINED_NODE if node does not exist. 
	 * @throws ClassCastException
	 *             when the corresponding element for given path is not a node.
	 */ 
	public static IBONode getIBONodeByPath(String path) throws ClassCastException { return ENUM_INSPECTOR.getIBONodeByPath(path); }

}

