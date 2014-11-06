package org.agito.bpmo.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.agito.bpmo.sample.invoice.bpmo.Invoice;
import org.agito.bpmo.sample.invoice.bpmo.InvoiceAccess;
import org.agito.bpmo.sample.invoice.bpmo.InvoiceLifecycle;
import org.agito.bpmo.sample.invoice.bpmo.InvoiceProcessActivity;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.agito.cps.commons.util.ResourceReaderUtils;
import de.agito.cps.core.bpmo.IBPMO;
import de.agito.cps.core.engine.identity.PrincipalType;
import de.agito.cps.core.exception.ValidationException;
import de.agito.cps.core.process.spi.eventing.ProcessAgentEventType;
import de.agito.cps.core.utils.ConvertUtils;
import de.agito.cps.process.camunda.test.BPMODeployment;
import de.agito.cps.process.camunda.test.BPMOTestUserId;
import de.agito.cps.test.camunda.BPMOProcessEngineRule;

public class InvoiceTest {

	@Rule
	public BPMOProcessEngineRule bpmoRule = new BPMOProcessEngineRule();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Deployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmn")
	@BPMODeployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmo")
	@BPMOTestUserId("bob")
	@Test
	public void testInvoiceStartPositive() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.createBPMO(Invoice.$BPMO, InvoiceLifecycle.NEW, "001");
		InvoiceAccess invoiceAccess = new InvoiceAccess(bpmo.getBPMOData());
		invoiceAccess.getInvoicingParty().setValue("ACME");
		invoiceAccess.getInvoiceNumber().setValue("10343456");
		invoiceAccess.getInvoiceDate().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getInvoiceReceived().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getTermOfPayment().setValue(new BigDecimal(30));
		invoiceAccess.getOrderNumber().setValue("90387595");
		invoiceAccess.getTaxPositions().getRows().get(0).getNetAmount().setValue(new BigDecimal("100.98"));
		try {
			invoiceAccess.getInvoiceAttachment().setValue("Invoice ACME Inc.pdf", "application/pdf",
					ResourceReaderUtils.getResourceAsBinaryByThread("Invoice ACME Inc.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start process
		bpmo.startProcess();
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

	}

	@Deployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmn")
	@BPMODeployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmo")
	@BPMOTestUserId("bob")
	@Test
	public void testSearchContent() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.createBPMO(Invoice.$BPMO, InvoiceLifecycle.NEW, "001");
		InvoiceAccess invoiceAccess = new InvoiceAccess(bpmo.getBPMOData());
		invoiceAccess.getInvoicingParty().setValue("ACME");
		invoiceAccess.getInvoiceNumber().setValue("10343456");
		invoiceAccess.getInvoiceDate().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getInvoiceReceived().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getTermOfPayment().setValue(new BigDecimal(30));
		invoiceAccess.getOrderNumber().setValue("90387595");
		invoiceAccess.getTaxPositions().getRows().get(0).getNetAmount().setValue(new BigDecimal("100.98"));
		try {
			invoiceAccess.getInvoiceAttachment().setValue("Invoice ACME Inc.pdf", "application/pdf",
					ResourceReaderUtils.getResourceAsBinaryByThread("Invoice ACME Inc.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start process
		bpmo.startProcess();
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createBPMOHeaderQuery()
				.contentValueEquals(invoiceAccess.getInvoicingParty().getDefinition().getDefinitionHash(), "ACME")
				.singleResult());

		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createBPMOHeaderQuery()
				.contentValueEquals(invoiceAccess.getInvoiceNumber().getDefinition().getDefinitionHash(), "10343456")
				.singleResult());

		Assert.assertNotNull(bpmoRule
				.getBpmoEngine()
				.getRuntimeService()
				.createBPMOHeaderQuery()
				.contentValueEquals(invoiceAccess.getTermOfPayment().getDefinition().getDefinitionHash(),
						new BigDecimal(30)).singleResult());

		Assert.assertNotNull(bpmoRule
				.getBpmoEngine()
				.getRuntimeService()
				.createBPMOHeaderQuery()
				.contentValueEquals(
						invoiceAccess.getBPMODefinition().getColumnDefinition(Invoice.TaxPositions$NetAmount)
								.getDefinitionHash(), new BigDecimal("100.98")).singleResult());

		Assert.assertNotNull(bpmoRule
				.getBpmoEngine()
				.getRuntimeService()
				.createBPMOHeaderQuery()
				.contentValueEquals(invoiceAccess.getInvoiceAttachment().getDefinition().getDefinitionHash(),
						"Invoice ACME Inc.pdf").singleResult());

	}

	@Deployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmn")
	@BPMODeployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmo")
	@BPMOTestUserId("bob")
	@Test
	public void testInvoiceStartNeagtive() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.createBPMO(Invoice.$BPMO, InvoiceLifecycle.NEW, "001");
		InvoiceAccess invoiceAccess = new InvoiceAccess(bpmo.getBPMOData());
		invoiceAccess.getInvoicingParty().setValue("ACME");
		invoiceAccess.getInvoiceNumber().setValue("10343456");
		invoiceAccess.getInvoiceDate().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getInvoiceReceived().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getTermOfPayment().setValue(new BigDecimal(30));
		invoiceAccess.getOrderNumber().setValue("90387595");
		invoiceAccess.getTaxPositions().getRows().get(0).getNetAmount().setValue(new BigDecimal("100.98"));

		// expect ValidationException upon startProcess()
		expectedException.expect(ValidationException.class);

		// start process
		bpmo.startProcess();
	}

	@Deployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmn")
	@BPMODeployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmo")
	@BPMOTestUserId("bob")
	@Test
	public void testInvoiceFinish() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.createBPMO(Invoice.$BPMO, InvoiceLifecycle.NEW, "001");
		InvoiceAccess invoiceAccess = new InvoiceAccess(bpmo.getBPMOData());
		invoiceAccess.getInvoicingParty().setValue("ACME");
		invoiceAccess.getInvoiceNumber().setValue("10343456");
		invoiceAccess.getInvoiceDate().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getInvoiceReceived().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getTermOfPayment().setValue(new BigDecimal(30));
		invoiceAccess.getOrderNumber().setValue("90387595");
		invoiceAccess.getTaxPositions().getRows().get(0).getNetAmount().setValue(new BigDecimal("100.98"));
		try {
			invoiceAccess.getInvoiceAttachment().setValue("Invoice ACME Inc.pdf", "application/pdf",
					ResourceReaderUtils.getResourceAsBinaryByThread("Invoice ACME Inc.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start process
		bpmo.startProcess();
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// wait for async jobs
		bpmoRule.waitForJobExecutorToProcessAllJobs(30000l, 200l);

		// checkout and complete Approval task
		bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true, InvoiceProcessActivity.Approval);
		bpmo.claimTaskInstance(InvoiceProcessActivity.Approval);
		bpmo.completeTaskInstance("approved", null);

		// wait for async jobs
		bpmoRule.waitForJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createBusinessLogQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).singleResult());

	}

	@Deployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmn")
	@BPMODeployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmo")
	@BPMOTestUserId("bob")
	@Test
	public void testInvoiceFinishIncludeOrderCheck() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.createBPMO(Invoice.$BPMO, InvoiceLifecycle.NEW, "001");
		InvoiceAccess invoiceAccess = new InvoiceAccess(bpmo.getBPMOData());
		invoiceAccess.getInvoicingParty().setValue("ACME");
		invoiceAccess.getInvoiceNumber().setValue("10343456");
		invoiceAccess.getInvoiceDate().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getInvoiceReceived().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getTermOfPayment().setValue(new BigDecimal(30));
		invoiceAccess.getTaxPositions().getRows().get(0).getNetAmount().setValue(new BigDecimal("100.98"));
		try {
			invoiceAccess.getInvoiceAttachment().setValue("Invoice ACME Inc.pdf", "application/pdf",
					ResourceReaderUtils.getResourceAsBinaryByThread("Invoice ACME Inc.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start process
		bpmo.startProcess();
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// wait for async jobs
		bpmoRule.waitForJobExecutorToProcessAllJobs(30000l, 200l);

		// checkout and complete CheckOrder task
		bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true, InvoiceProcessActivity.CheckOrder);
		bpmo.claimTaskInstance(InvoiceProcessActivity.CheckOrder);
		invoiceAccess = new InvoiceAccess(bpmo.getBPMOData());
		invoiceAccess.getOrderNumber().setValue("90387595");
		invoiceAccess.getOrderProfitcenter().setValue("100012345");
		invoiceAccess.getOrderCostCenter().setValue("3453464");
		invoiceAccess.getOrderChecked().setValue(true);
		invoiceAccess.getShipmentChecked().setValue(true);
		invoiceAccess.getApprover().setValue("alice", PrincipalType.USER);
		bpmo.completeTaskInstance("continue", null);

		// checkout and complete Approval task
		bpmoRule.switchUser("alice");
		bpmo.claimTaskInstance(InvoiceProcessActivity.Approval);
		bpmo.completeTaskInstance("approved", null);

		// wait for async jobs
		bpmoRule.waitForJobExecutorToProcessAllJobs(30000l, 200l);

		// assert process end
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_END)
				.singleResult());

	}

	@Deployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmn")
	@BPMODeployment(resources = "org/agito/bpmo/sample/invoice/bpmo/Invoice.bpmo")
	@BPMOTestUserId("bob")
	@Test
	public void testInvoiceDisapproveAndCancel() {

		// create bpmo
		IBPMO bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.createBPMO(Invoice.$BPMO, InvoiceLifecycle.NEW, "001");
		InvoiceAccess invoiceAccess = new InvoiceAccess(bpmo.getBPMOData());
		invoiceAccess.getInvoicingParty().setValue("ACME");
		invoiceAccess.getInvoiceNumber().setValue("10343456");
		invoiceAccess.getInvoiceDate().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getInvoiceReceived().setValue(ConvertUtils.getXMLGregCalFromDate(new Date()));
		invoiceAccess.getTermOfPayment().setValue(new BigDecimal(30));
		invoiceAccess.getOrderNumber().setValue("90387595");
		invoiceAccess.getTaxPositions().getRows().get(0).getNetAmount().setValue(new BigDecimal("100.98"));
		try {
			invoiceAccess.getInvoiceAttachment().setValue("Invoice ACME Inc.pdf", "application/pdf",
					ResourceReaderUtils.getResourceAsBinaryByThread("Invoice ACME Inc.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start process
		bpmo.startProcess();
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.processInstanceId(bpmo.getBPMOHeader().getProcessInstanceId())
				.eventType(ProcessAgentEventType.PROCESS_START).singleResult());

		// wait for async jobs
		bpmoRule.waitForJobExecutorToProcessAllJobs(30000l, 200l);

		// checkout and complete Approval task
		bpmo = bpmoRule.getBpmoEngine().getRuntimeService()
				.readBPMO(bpmo.getBPMOHeader().getBPMOUuid(), true, InvoiceProcessActivity.Approval);
		bpmo.claimTaskInstance(InvoiceProcessActivity.Approval);
		bpmo.completeTaskInstance("disapproved", "not approved");

		// checkout and complete CheckOrder task
		bpmo.claimTaskInstance(InvoiceProcessActivity.CheckOrder);
		bpmo.completeTaskInstance("cancel", "was wrong");

		// assert process end
		Assert.assertNotNull(bpmoRule.getBpmoEngine().getRuntimeService().createProcessHistoryQuery()
				.bpmoUuid(bpmo.getBPMOHeader().getBPMOUuid()).eventType(ProcessAgentEventType.PROCESS_CANCEL)
				.singleResult());

	}
}