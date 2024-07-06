import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DocumentTransaction from './document-transaction';
import DocumentTransactionDetail from './document-transaction-detail';
import DocumentTransactionUpdate from './document-transaction-update';
import DocumentTransactionDeleteDialog from './document-transaction-delete-dialog';

const DocumentTransactionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DocumentTransaction />} />
    <Route path="new" element={<DocumentTransactionUpdate />} />
    <Route path=":id">
      <Route index element={<DocumentTransactionDetail />} />
      <Route path="edit" element={<DocumentTransactionUpdate />} />
      <Route path="delete" element={<DocumentTransactionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DocumentTransactionRoutes;
