import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftReceipt from './draft-receipt';
import DraftReceiptDetail from './draft-receipt-detail';
import DraftReceiptUpdate from './draft-receipt-update';
import DraftReceiptDeleteDialog from './draft-receipt-delete-dialog';

const DraftReceiptRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftReceipt />} />
    <Route path="new" element={<DraftReceiptUpdate />} />
    <Route path=":id">
      <Route index element={<DraftReceiptDetail />} />
      <Route path="edit" element={<DraftReceiptUpdate />} />
      <Route path="delete" element={<DraftReceiptDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftReceiptRoutes;
