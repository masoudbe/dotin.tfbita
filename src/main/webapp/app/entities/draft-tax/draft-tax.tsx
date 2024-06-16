import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-tax.reducer';

export const DraftTax = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftTaxList = useAppSelector(state => state.draftTax.entities);
  const loading = useAppSelector(state => state.draftTax.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="draft-tax-heading" data-cy="DraftTaxHeading">
        <Translate contentKey="tfbitaApp.draftTax.home.title">Draft Taxes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftTax.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/draft-tax/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftTax.home.createLabel">Create new Draft Tax</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftTaxList && draftTaxList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftTax.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('orderRegCurrencyAmount')}>
                  <Translate contentKey="tfbitaApp.draftTax.orderRegCurrencyAmount">Order Reg Currency Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegCurrencyAmount')} />
                </th>
                <th className="hand" onClick={sort('mainAccountCurrencyAmount')}>
                  <Translate contentKey="tfbitaApp.draftTax.mainAccountCurrencyAmount">Main Account Currency Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mainAccountCurrencyAmount')} />
                </th>
                <th className="hand" onClick={sort('letterNumber')}>
                  <Translate contentKey="tfbitaApp.draftTax.letterNumber">Letter Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('letterNumber')} />
                </th>
                <th className="hand" onClick={sort('letterImage')}>
                  <Translate contentKey="tfbitaApp.draftTax.letterImage">Letter Image</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('letterImage')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="tfbitaApp.draftTax.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('registrationDate')}>
                  <Translate contentKey="tfbitaApp.draftTax.registrationDate">Registration Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('registrationDate')} />
                </th>
                <th className="hand" onClick={sort('returnTaxesAmount')}>
                  <Translate contentKey="tfbitaApp.draftTax.returnTaxesAmount">Return Taxes Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('returnTaxesAmount')} />
                </th>
                <th className="hand" onClick={sort('orderRegRate')}>
                  <Translate contentKey="tfbitaApp.draftTax.orderRegRate">Order Reg Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegRate')} />
                </th>
                <th className="hand" onClick={sort('mainAccountRate')}>
                  <Translate contentKey="tfbitaApp.draftTax.mainAccountRate">Main Account Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mainAccountRate')} />
                </th>
                <th className="hand" onClick={sort('documentTransactionNumber')}>
                  <Translate contentKey="tfbitaApp.draftTax.documentTransactionNumber">Document Transaction Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('documentTransactionNumber')} />
                </th>
                <th className="hand" onClick={sort('returnDocumentTransactionNumber')}>
                  <Translate contentKey="tfbitaApp.draftTax.returnDocumentTransactionNumber">Return Document Transaction Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('returnDocumentTransactionNumber')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftTax.taxes">Taxes</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftTaxList.map((draftTax, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-tax/${draftTax.id}`} color="link" size="sm">
                      {draftTax.id}
                    </Button>
                  </td>
                  <td>{draftTax.orderRegCurrencyAmount}</td>
                  <td>{draftTax.mainAccountCurrencyAmount}</td>
                  <td>{draftTax.letterNumber}</td>
                  <td>
                    {draftTax.letterImage ? (
                      <div>
                        {draftTax.letterImageContentType ? (
                          <a onClick={openFile(draftTax.letterImageContentType, draftTax.letterImage)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {draftTax.letterImageContentType}, {byteSize(draftTax.letterImage)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{draftTax.description}</td>
                  <td>{draftTax.registrationDate}</td>
                  <td>{draftTax.returnTaxesAmount ? 'true' : 'false'}</td>
                  <td>{draftTax.orderRegRate}</td>
                  <td>{draftTax.mainAccountRate}</td>
                  <td>{draftTax.documentTransactionNumber}</td>
                  <td>{draftTax.returnDocumentTransactionNumber}</td>
                  <td>{draftTax.taxes ? <Link to={`/draft/${draftTax.taxes.id}`}>{draftTax.taxes.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/draft-tax/${draftTax.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/draft-tax/${draftTax.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft-tax/${draftTax.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.draftTax.home.notFound">No Draft Taxes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftTax;
