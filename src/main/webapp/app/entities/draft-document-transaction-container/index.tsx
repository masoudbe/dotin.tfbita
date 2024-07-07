import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftDocumentTransactionContainer from './draft-document-transaction-container';
import DraftDocumentTransactionContainerDetail from './draft-document-transaction-container-detail';
import DraftDocumentTransactionContainerUpdate from './draft-document-transaction-container-update';
import DraftDocumentTransactionContainerDeleteDialog from './draft-document-transaction-container-delete-dialog';

const DraftDocumentTransactionContainerRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftDocumentTransactionContainer />} />
    <Route path="new" element={<DraftDocumentTransactionContainerUpdate />} />
    <Route path=":id">
      <Route index element={<DraftDocumentTransactionContainerDetail />} />
      <Route path="edit" element={<DraftDocumentTransactionContainerUpdate />} />
      <Route path="delete" element={<DraftDocumentTransactionContainerDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftDocumentTransactionContainerRoutes;
