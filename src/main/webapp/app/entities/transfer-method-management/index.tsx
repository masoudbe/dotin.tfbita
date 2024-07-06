import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TransferMethodManagement from './transfer-method-management';
import TransferMethodManagementDetail from './transfer-method-management-detail';
import TransferMethodManagementUpdate from './transfer-method-management-update';
import TransferMethodManagementDeleteDialog from './transfer-method-management-delete-dialog';

const TransferMethodManagementRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TransferMethodManagement />} />
    <Route path="new" element={<TransferMethodManagementUpdate />} />
    <Route path=":id">
      <Route index element={<TransferMethodManagementDetail />} />
      <Route path="edit" element={<TransferMethodManagementUpdate />} />
      <Route path="delete" element={<TransferMethodManagementDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TransferMethodManagementRoutes;
