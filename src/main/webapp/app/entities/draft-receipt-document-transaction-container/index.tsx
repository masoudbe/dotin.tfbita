import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftReceiptDocumentTransactionContainer from './draft-receipt-document-transaction-container';
import DraftReceiptDocumentTransactionContainerDetail from './draft-receipt-document-transaction-container-detail';
import DraftReceiptDocumentTransactionContainerUpdate from './draft-receipt-document-transaction-container-update';
import DraftReceiptDocumentTransactionContainerDeleteDialog from './draft-receipt-document-transaction-container-delete-dialog';

const DraftReceiptDocumentTransactionContainerRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftReceiptDocumentTransactionContainer />} />
    <Route path="new" element={<DraftReceiptDocumentTransactionContainerUpdate />} />
    <Route path=":id">
      <Route index element={<DraftReceiptDocumentTransactionContainerDetail />} />
      <Route path="edit" element={<DraftReceiptDocumentTransactionContainerUpdate />} />
      <Route path="delete" element={<DraftReceiptDocumentTransactionContainerDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftReceiptDocumentTransactionContainerRoutes;
